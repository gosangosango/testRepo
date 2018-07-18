/**
 * Copyright (c) 1998-2018 Miracom Inc. All rights reserved.
 *
 * Don't copy or redistribute this source code without permission.
 * This software is provided "As Is" and any expressed or implied
 * warranties, including, but not limited to, the implied warranties of
 * merchantability and fitness for a particular purpose are disclaimed.
 * In no event shall Miracom Inc. or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential
 * damages including, but not limited to, procurement of substitute
 * goods or services; loss of use, data, or profits; or business
 * interruption) however caused and on any theory of liability, whether
 * in contract, strict liability, or tort (including negligence or otherwise)
 * arising in any way out of the use of this software, even if advised
 * of the possibility of such damage.
 *
 * For more information on this product, please see
 * http://www.miracom.co.kr
 */
package kr.co.miracom.framework.tool;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.converter.jackson.DateStrSerializer;
import kr.co.miracom.framework.service.GetListIn;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ReflectionUtils;
import kr.co.miracom.framework.util.SrcGenUtils;
import kr.co.miracom.framework.util.ValueUtils;

/**
 * @author myjung.jung
 */
public class SetupServiceGen extends AbstractTool {
    private static final Logger logger = LoggerFactory.getLogger(SetupServiceGen.class);

    private static final String tab = SrcGenUtils.tab;
    private static final String rn = "\r\n";
    private static final String rnt = rn + tab;
    private static final String rnt2 = rn + tab + tab;
    private static final String rnt5 = rn + tab + tab + tab + tab + tab;

    public static void main(String[] args) {
        try {
            gen(args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Good-bye!");
        }
    }

    private static class SubResource {
        private String resource;
        private String resourcePl;
        private ClassDef modelClass;
        private ClassDef vmodelClass;
        private ClassDef serviceClass;
    }

    public static void gen(String[] args) throws Exception {
        String pkg = scan("package");
        String prodPkg;
        String prodName;
        String module;
        {
            module = pkg.substring(pkg.lastIndexOf('.') + 1);
            prodPkg = pkg.substring(0, pkg.lastIndexOf('.'));
            prodName = prodPkg.substring(prodPkg.lastIndexOf('.') + 1);
            if (prodName.length() > 3) {
                prodPkg = pkg;
                prodName = module;
            }
        }

        String resource = StringUtils.capitalize(scan("resource"));
        String usRsc = ValueUtils.toDelimited(resource, '_');
        String rscPl = StringUtils.uncapitalize(scan("resource-pl"));
        // String bridge = ValueUtils.toBoolean(scan("simple resource?")) ? "simple" : "complex";

        ClassDef modelClass = scanModelClass(pkg + ".model", "model");

        String pkgPrefix = pkg + ".resource.simple." + usRsc;

        List<ClassDef> classes = new ArrayList<>();
        ClassDef ctrlInfClass = new ClassDef(pkgPrefix + ".controller", resource + "Controller");
        classes.add(ctrlInfClass);
        ClassDef ctrlImplClass = new ClassDef(pkgPrefix + ".controller", resource + "ControllerImpl");
        classes.add(ctrlImplClass);
        ClassDef ctrlStubClass = new ClassDef(pkgPrefix + ".controller", resource + "ControllerStub");
        classes.add(ctrlStubClass);
        ClassDef vmodelClass = new ClassDef(pkgPrefix + ".model", resource);
        classes.add(vmodelClass);
        ClassDef dmodelClass = new ClassDef(pkgPrefix + ".model", resource + "Detail");
        classes.add(dmodelClass);
        ClassDef serviceClass = new ClassDef(pkgPrefix + ".service", resource + "Service");
        classes.add(serviceClass);
        ClassDef getListClass = new ClassDef(pkgPrefix + ".service.get_list", "Get" + resource + "List");
        classes.add(getListClass);
        ClassDef getListInClass = new ClassDef(pkgPrefix + ".service.get_list", "Get" + resource + "ListIn");
        classes.add(getListInClass);

        List<SubResource> subResources = new ArrayList<>();
        while (ValueUtils.toBoolean(scan("more sub-resources exists?"))) {
            SubResource subRsc = new SubResource();

            subRsc.resource = StringUtils.capitalize(scan("sub-resource"));
            subRsc.resourcePl = StringUtils.uncapitalize(scan("sub-resource-pl"));
            subRsc.modelClass = scanModelClass(pkg + ".model", "sub-model");

            subRsc.vmodelClass = new ClassDef(pkgPrefix + ".model", resource + subRsc.resource);
            classes.add(subRsc.vmodelClass);
            subRsc.serviceClass = new ClassDef(pkgPrefix + ".service", resource + subRsc.resource + "Service");
            classes.add(subRsc.serviceClass);

            subResources.add(subRsc);
        }

        boolean overwrite = ValueUtils.toBoolean(scan("overwrite?"));

        {
            StringBuffer buf = new StringBuffer();
            buf.append("\r\nStart Generating Classes:");
            for (ClassDef clazz : classes) {
                buf.append("\r\n\t").append(clazz);
            }
            buf.append("\r\n");
            logger.info(buf.toString());
        }

        int i = 0;

        logger.info("\r\n" + ++i + ". Generating " + ctrlInfClass + " Source\r\n");
        {
            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, List.class, DeleteMapping.class, GetMapping.class, PathVariable.class,
                            PostMapping.class, PutMapping.class, RequestBody.class, RequestMapping.class,
                            RequestParam.class, Api.class, ApiImplicitParam.class, ApiImplicitParams.class,
                            ApiOperation.class, SelectOptions.class, GetListOut.class,
                            prodPkg + ".config." + StringUtils.capitalize(prodName) + "Const", vmodelClass.toString(),
                            dmodelClass.toString(), getListInClass.toString());
            for (SubResource subRsc : subResources) {
                imports.add(subRsc.vmodelClass.toString());
            }

            StringBuffer abuf = new StringBuffer();
            abuf.append(rn).append("@Api(value = \"Creating, Retrieving, Updating and Deleting ")
                            .append(StringUtils.capitalize(rscPl)).append(".\")");
            abuf.append(rn).append("@RequestMapping(path = ").append(StringUtils.capitalize(prodName))
                            .append("Const.VER_PATH").append(")");

            StringBuffer buf = new StringBuffer();
            buf.append(rnt).append("static final String RESOURCE = \"").append(module).append("/").append(rscPl)
                            .append("\";");
            buf.append(rn);

            buf.append(rnt).append("@GetMapping(path = RESOURCE + \"/{id}\")");
            buf.append(rnt).append("@ApiOperation(\"Get ").append(resource).append("\")");
            buf.append(rnt).append(
                            "@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"ID\", required = true),");
            buf.append(rnt5).append("@ApiImplicitParam(name = \"select\", value = \"Field Names of ").append(resource)
                            .append(" for Select\", allowMultiple = true),");
            buf.append(rnt5).append("@ApiImplicitParam(name = \"unselect\", value = \"Field Names of ").append(resource)
                            .append(" for Unselect\", allowMultiple = true) })");
            buf.append(rnt).append(dmodelClass.getSimpleName())
                            .append(" get(@PathVariable String id, SelectOptions options) throws Exception;");
            buf.append(rn);

            buf.append(rnt).append("@PutMapping(path = RESOURCE + \"/{id}\")");
            buf.append(rnt).append("@ApiOperation(\"Update ").append(resource).append("\")");
            buf.append(rnt).append(
                            "@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"ID\", required = true),");
            buf.append(rnt5).append("@ApiImplicitParam(name = \"data\", value = \"").append(resource)
                            .append(" Json\", dataType = \"").append(dmodelClass.getSimpleName())
                            .append("\", required = true),");
            buf.append(rnt5).append("@ApiImplicitParam(name = \"fieldName\", value = \"Field Names of ")
                            .append(resource).append(" for Update\", allowMultiple = true) })");
            buf.append(rnt).append("void put(@PathVariable String id, @RequestBody ")
                            .append(dmodelClass.getSimpleName())
                            .append(" data, @RequestParam(required = false) String[] fieldName) throws Exception;");
            buf.append(rn);

            buf.append(rnt).append("@GetMapping(path = RESOURCE)");
            buf.append(rnt).append("@ApiOperation(\"Get ").append(resource).append(" List\")");
            buf.append(rnt).append("GetListOut<").append(vmodelClass.getSimpleName()).append("> getList(")
                            .append(getListInClass.getSimpleName()).append(" input) throws Exception;");
            buf.append(rn);
            buf.append(rnt).append("@PostMapping(path = RESOURCE + \"/save\")");
            buf.append(rnt).append("@ApiOperation(\"Save ").append(resource).append(" List\")");
            buf.append(rnt).append("@ApiImplicitParams({");
            buf.append(rnt5).append("@ApiImplicitParam(name = \"list\", value = \"").append(resource)
                            .append(" List\", dataType = \"").append(vmodelClass.getSimpleName())
                            .append("\", required = true, allowMultiple = true),");
            buf.append(rnt5).append("@ApiImplicitParam(name = \"fieldName\", value = \"Field Names of ")
                            .append(resource).append(" for Save\", allowMultiple = true) })");
            buf.append(rnt).append("void saveList(@RequestBody List<").append(vmodelClass.getSimpleName())
                            .append("> list, @RequestParam(required = false) String[] fieldName) throws Exception;");
            buf.append(rn);

            buf.append(rnt).append("@DeleteMapping(path = RESOURCE)");
            buf.append(rnt).append("@ApiOperation(\"Delete ").append(resource).append(" List\")");
            buf.append(rnt).append("@ApiImplicitParams({");
            buf.append(rnt5).append("@ApiImplicitParam(name = \"list\", value = \"").append(resource)
                            .append(" List\", dataType = \"").append(vmodelClass.getSimpleName())
                            .append("\", required = true, allowMultiple = true) })");
            buf.append(rnt).append("void deleteList(@RequestBody List<").append(vmodelClass.getSimpleName())
                            .append("> list) throws Exception;");
            buf.append(rn);

            for (SubResource subRsc : subResources) {
                buf.append(rnt).append("@GetMapping(path = RESOURCE + \"/{id}/").append(subRsc.resourcePl)
                                .append("\")");
                buf.append(rnt).append("@ApiOperation(\"Get ").append(resource).append(" ").append(subRsc.resource)
                                .append(" List\")");
                buf.append(rnt).append("@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"ID of ")
                                .append(resource).append("\", required = true) })");
                buf.append(rnt).append("GetListOut<").append(subRsc.vmodelClass.getSimpleName()).append("> get")
                                .append(subRsc.resource).append("List(@PathVariable String id) throws Exception;");
                buf.append(rn);

                buf.append(rnt).append("@PostMapping(path = RESOURCE + \"/{id}/").append(subRsc.resourcePl)
                                .append("/save\")");
                buf.append(rnt).append("@ApiOperation(\"Save ").append(resource).append(" ").append(subRsc.resource)
                                .append(" List\")");
                buf.append(rnt).append("@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"ID of ")
                                .append(resource).append("\", required = true),");
                buf.append(rnt5).append("@ApiImplicitParam(name = \"list\", value = \"").append(resource).append(" ")
                                .append(subRsc.resource).append(" List\", dataType = \"")
                                .append(subRsc.vmodelClass.getSimpleName()).append("\", allowMultiple = true),");
                buf.append(rnt5).append("@ApiImplicitParam(name = \"fieldName\", value = \"Field Names of ")
                                .append(subRsc.resource).append(" for Save\", allowMultiple = true) })");
                buf.append(rnt).append("void save").append(subRsc.resource)
                                .append("List(@PathVariable String id, @RequestBody List<")
                                .append(subRsc.vmodelClass.getSimpleName())
                                .append("> list, @RequestParam(required = false) String[] fieldName) throws Exception;");
                buf.append(rn);
            }

            String src = SrcGenUtils.toClassSrc("Setup Controller", resource, true, ctrlInfClass.toString(), null, null,
                            imports, abuf.toString(), buf.toString());
            ctrlInfClass.setSrc(src);

        }

        logger.info("\r\n" + ++i + ". Generating " + ctrlImplClass + " Source\r\n");
        {
            List<String> infs = new ArrayList<>();
            infs.add(ctrlInfClass.toString());

            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, List.class, CrossOrigin.class, PathVariable.class, RequestBody.class,
                            RequestParam.class, RestController.class, SelectOptions.class, GetListOut.class,
                            BeanUtils.class, vmodelClass.toString(), dmodelClass.toString(), serviceClass.toString(),
                            getListClass.toString(), getListInClass.toString());
            for (SubResource subRsc : subResources) {
                imports.add(subRsc.serviceClass.toString());
                imports.add(subRsc.vmodelClass.toString());
            }

            StringBuffer abuf = new StringBuffer();
            abuf.append(rn).append("@CrossOrigin");
            abuf.append(rn).append("@RestController");

            StringBuffer buf = new StringBuffer();
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public ").append(dmodelClass.getSimpleName())
                            .append(" get(@PathVariable String id, SelectOptions options) throws Exception {");
            buf.append(rnt2).append("return BeanUtils.get(").append(serviceClass.getSimpleName())
                            .append(".class).get(id, options);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public void put(@PathVariable String id, @RequestBody ")
                            .append(dmodelClass.getSimpleName())
                            .append(" data, @RequestParam(required = false) String[] fieldName) throws Exception {");
            buf.append(rnt2).append("BeanUtils.get(").append(serviceClass.getSimpleName())
                            .append(".class).put(id, data, fieldName);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public GetListOut<").append(vmodelClass.getSimpleName()).append("> getList(")
                            .append(getListInClass.getSimpleName()).append(" input) throws Exception {");
            buf.append(rnt2).append("return BeanUtils.get(").append(getListClass.getSimpleName())
                            .append(".class).getList(input);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public void saveList(@RequestBody List<").append(vmodelClass.getSimpleName())
                            .append("> list, @RequestParam(required = false) String[] fieldName) throws Exception {");
            buf.append(rnt2).append("BeanUtils.get(").append(serviceClass.getSimpleName())
                            .append(".class).saveList(list, fieldName);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public void deleteList(@RequestBody List<").append(vmodelClass.getSimpleName())
                            .append("> list) throws Exception {");
            buf.append(rnt2).append("BeanUtils.get(").append(serviceClass.getSimpleName())
                            .append(".class).deleteList(list);");
            buf.append(rnt).append("}");
            buf.append(rn);

            for (SubResource subRsc : subResources) {
                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public GetListOut<").append(subRsc.vmodelClass.getSimpleName()).append("> get")
                                .append(subRsc.resource).append("List(@PathVariable String id) throws Exception {");
                buf.append(rnt2).append("return BeanUtils.get(").append(subRsc.serviceClass.getSimpleName())
                                .append(".class).getList(id);");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public void save").append(subRsc.resource)
                                .append("List(@PathVariable String id, @RequestBody List<")
                                .append(subRsc.vmodelClass.getSimpleName())
                                .append("> list, @RequestParam(required = false) String[] fieldName) throws Exception {");
                buf.append(rnt2).append("BeanUtils.get(").append(subRsc.serviceClass.getSimpleName())
                                .append(".class).saveList(id, list, fieldName);");
                buf.append(rnt).append("}");
                buf.append(rn);
            }

            String src = SrcGenUtils.toClassSrc("Setup Controller", resource, false, ctrlImplClass.toString(), null,
                            infs, imports, abuf.toString(), buf.toString());
            ctrlImplClass.setSrc(src);
        }

        logger.info("\r\n" + ++i + ". Generating " + ctrlStubClass + " Source\r\n");
        {
            List<String> infs = new ArrayList<>();
            infs.add(ctrlInfClass.toString());

            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, List.class, SelectOptions.class, GetListOut.class, vmodelClass.toString(),
                            dmodelClass.toString(), getListInClass.toString());
            for (SubResource subRsc : subResources) {
                SrcGenUtils.addImports(imports, subRsc.vmodelClass.toString());
            }

            StringBuffer buf = new StringBuffer();
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public ").append(dmodelClass.getSimpleName())
                            .append(" get(String id, SelectOptions options) throws Exception {");
            buf.append(rnt2).append("return null;");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public void put(String id, ").append(dmodelClass.getSimpleName())
                            .append(" data, String[] fieldName) throws Exception {");
            buf.append(rn);
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public GetListOut<").append(vmodelClass.getSimpleName()).append("> getList(")
                            .append(getListInClass.getSimpleName()).append(" input) throws Exception {");
            buf.append(rnt2).append("return null;");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public void saveList(List<").append(vmodelClass.getSimpleName())
                            .append("> list, String[] fieldName) throws Exception {");
            buf.append(rn);
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("@Override");
            buf.append(rnt).append("public void deleteList(List<").append(vmodelClass.getSimpleName())
                            .append("> list) throws Exception {");
            buf.append(rn);
            buf.append(rnt).append("}");
            buf.append(rn);

            for (SubResource subRsc : subResources) {
                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public GetListOut<").append(subRsc.vmodelClass.getSimpleName()).append("> get")
                                .append(subRsc.resource).append("List(String id) throws Exception {");
                buf.append(rnt2).append("return null;");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public void save").append(subRsc.resource).append("List(String id, List<")
                                .append(subRsc.vmodelClass.getSimpleName())
                                .append("> list, String[] fieldName) throws Exception {");
                buf.append(rn);
                buf.append(rnt).append("}");
                buf.append(rn);
            }

            String src = SrcGenUtils.toClassSrc("Setup Controller", resource, false, ctrlStubClass.toString(), null,
                            infs, imports, null, buf.toString());
            ctrlStubClass.setSrc(src);
        }

        logger.info("\r\n" + ++i + ". Generating " + vmodelClass + " Source\r\n");
        {
            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, "lombok.Data");

            StringBuffer abuf = new StringBuffer();
            abuf.append(rn).append("@Data");

            StringBuffer buf = new StringBuffer();
            buf.append(rnt).append("private String id;");

            Class<?> clazz = modelClass.getClazz();
            List<Field> fieldList = ReflectionUtils.getFieldList(clazz, false);
            for (Field field : fieldList) {
                if ("factoryCode".equals(field.getName())) {
                    continue;
                }

                if (field.getAnnotation(DbistRelation.class) != null) {
                    continue;
                }

                Class<?> fieldType = field.getType();
                SrcGenUtils.addImports(imports, fieldType);
                if (ZonedDateTime.class.equals(fieldType)) {
                    SrcGenUtils.addImports(imports, JsonFormat.class);
                    buf.append(rnt).append("@JsonFormat(pattern = \"yyyy-MM-dd'T'HH:mm:ssZ\")");
                } else if (field.getName().endsWith("Date") && String.class.equals(fieldType)) {
                    SrcGenUtils.addImports(imports, JsonSerialize.class);
                    SrcGenUtils.addImports(imports, DateStrSerializer.class);
                    buf.append(rnt).append("@JsonSerialize(using = DateStrSerializer.class)");
                    SrcGenUtils.addImports(imports, JsonDeserialize.class);
                    SrcGenUtils.addImports(imports, DateStrDeserializer.class);
                    buf.append(rnt).append("@JsonDeserialize(using = DateStrDeserializer.class)");
                }
                buf.append(rnt).append("private ").append(fieldType.getSimpleName()).append(" ").append(field.getName())
                                .append(";");
            }

            String src = SrcGenUtils.toClassSrc("Item VO", resource, false, vmodelClass.toString(), null, null, imports,
                            abuf.toString(), buf.toString());
            vmodelClass.setSrc(src);
        }

        logger.info("\r\n" + ++i + ". Generating " + dmodelClass + " Source\r\n");
        {
            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, "lombok.Getter", "lombok.Setter");

            StringBuffer abuf = new StringBuffer();
            abuf.append(rn).append("@Getter");
            abuf.append(rn).append("@Setter");

            StringBuffer buf = new StringBuffer();

            // for (SubResource subRsc : subResources) {
            // buf.append(rnt).append("private List<").append(subRsc.vmodelClass.getSimpleName()).append("> ")
            // .append(StringUtils.uncapitalize(subRsc.resource)).append("List;");
            // }

            String src = SrcGenUtils.toClassSrc("Detail VO", resource, false, dmodelClass.toString(),
                            vmodelClass.toString(), null, imports, abuf.toString(), buf.toString());
            dmodelClass.setSrc(src);
        }

        logger.info("\r\n" + ++i + ". Generating " + serviceClass + " Source\r\n");
        {
            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, List.class, SelectOptions.class, RestService.class, modelClass.toString(),
                            vmodelClass.toString(), dmodelClass.toString());

            StringBuffer buf = new StringBuffer();
            buf.append(rnt).append("private RestService<").append(modelClass.getSimpleName()).append(", ")
                            .append(vmodelClass.getSimpleName()).append(", ").append(dmodelClass.getSimpleName())
                            .append("> restService = new RestService<>(").append(modelClass.getSimpleName())
                            .append(".class, ").append(vmodelClass.getSimpleName()).append(".class, ")
                            .append(dmodelClass.getSimpleName()).append(".class);");
            buf.append(rn);

            buf.append(rnt).append("public ").append(serviceClass.getSimpleName()).append("() {");
            buf.append(rnt2).append("// restService.setIdValueRule(\"UPPER\");");
            buf.append(rnt2).append("// restService.setPkFieldName(\"id\");");
            buf.append(rnt2).append("// restService.addFieldPattern(\"id\", \"^[-A-Z0-9.]*$\");");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public ").append(dmodelClass.getSimpleName())
                            .append(" get(String id, SelectOptions options) throws Exception {");
            buf.append(rnt2).append("return restService.get(id, options);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public void post(String id, ").append(dmodelClass.getSimpleName())
                            .append(" data, String[] fieldName) throws Exception {");
            buf.append(rnt2).append("restService.post(id, data, fieldName);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public void put(String id, ").append(dmodelClass.getSimpleName())
                            .append(" data, String[] fieldName) throws Exception {");
            buf.append(rnt2).append("restService.put(id, data, fieldName);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public void delete(String id, ").append(dmodelClass.getSimpleName())
                            .append(" data) throws Exception {");
            buf.append(rnt2).append("restService.delete(id, data);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public void postList(List<").append(vmodelClass.getSimpleName())
                            .append("> list, String[] fieldName) throws Exception {");
            buf.append(rnt2).append("restService.postList(list, fieldName);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public void putList(List<").append(vmodelClass.getSimpleName())
                            .append("> list, String[] fieldName) throws Exception {");
            buf.append(rnt2).append("restService.putList(list, fieldName);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public void saveList(List<").append(vmodelClass.getSimpleName())
                            .append("> list, String[] fieldName) throws Exception {");
            buf.append(rnt2).append("restService.saveList(list, fieldName);");
            buf.append(rnt).append("}");
            buf.append(rn);

            buf.append(rnt).append("public void deleteList(List<").append(vmodelClass.getSimpleName())
                            .append("> list) throws Exception {");
            buf.append(rnt2).append("restService.deleteList(list);");
            buf.append(rnt).append("}");
            buf.append(rn);

            String src = SrcGenUtils.toClassSrc("Setup Service", resource, false, serviceClass.toString(), null, null,
                            imports, null, buf.toString());
            serviceClass.setSrc(src);
        }

        logger.info("\r\n" + ++i + ". Generating " + getListClass + " Source\r\n");
        {
            Class<?> clazz = modelClass.getClazz();
            boolean factoryFlag = ReflectionUtils.getField(clazz, "factoryCode") != null;

            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, Page.class, Query.class, GetListOut.class, DbUtils.class, ValueUtils.class,
                            modelClass.toString(), vmodelClass.toString());
            if (factoryFlag) {
                SrcGenUtils.addImports(imports, AuthUtils.class);
            }

            StringBuffer buf = new StringBuffer();
            buf.append(rnt).append("public GetListOut<").append(vmodelClass.getSimpleName()).append("> getList(")
                            .append(getListInClass.getSimpleName()).append(" input) throws Exception {");
            buf.append(rnt2).append("Query query = DbUtils.newQuery(input);");

            if (factoryFlag) {
                buf.append(rnt2).append("query.addFilter(\"factoryCode\", AuthUtils.getFactoryCode());");
            }

            String orderBy = null;
            List<Field> fieldList = ReflectionUtils.getFieldList(clazz, false);
            for (Field field : fieldList) {
                if ("factoryCode".equals(field.getName())) {
                    continue;
                }

                if (field.getAnnotation(DbistRelation.class) != null) {
                    continue;
                }

                String fieldName = field.getName();
                String cfieldName = StringUtils.capitalize(fieldName);
                if (fieldName.equals("deleteFlag")) {
                    buf.append(rnt2).append("if (!input.isIncludeDeleteFlag()) {");
                    buf.append(rnt2).append(tab).append("query.addFilter(\"deleteFlag\", false);");
                    buf.append(rnt2).append("}");
                } else if (field.getType().equals(String.class)
                                && !(fieldName.endsWith("Type") || fieldName.endsWith("Status"))) {
                    buf.append(rnt2).append("// DbUtils.addContains(query, \"").append(fieldName)
                                    .append("\", input.get").append(cfieldName).append("());");
                } else if (field.getType().equals(boolean.class)) {
                    buf.append(rnt2).append("// DbUtils.addEqualsIfTrue(query, \"").append(fieldName)
                                    .append("\", input.is").append(cfieldName).append("());");
                } else {
                    buf.append(rnt2).append("// DbUtils.addEquals(query, \"").append(fieldName).append("\", input.get")
                                    .append(cfieldName).append("());");
                }
                if (orderBy == null) {
                    orderBy = fieldName;
                }
            }
            buf.append(rnt2).append("query.addOrder(\"").append(orderBy).append("\", true);");

            buf.append(rnt2).append("Page<").append(vmodelClass.getSimpleName()).append("> page = DbUtils.selectPage(")
                            .append(modelClass.getSimpleName()).append(".class, query, ")
                            .append(vmodelClass.getSimpleName()).append(".class);");
            buf.append(rnt2).append("GetListOut<").append(vmodelClass.getSimpleName())
                            .append("> output = new GetListOut<>();");
            buf.append(rnt2).append("ValueUtils.populate(page, output);");
            buf.append(rnt2).append("return output;");
            buf.append(rnt).append("}");

            String src = SrcGenUtils.toClassSrc("Get List Service", resource, false, getListClass.toString(), null,
                            null, imports, null, buf.toString());
            getListClass.setSrc(src);
        }

        logger.info("\r\n" + ++i + ". Generating " + getListInClass + " Source\r\n");
        {
            List<String> imports = new ArrayList<String>();
            SrcGenUtils.addImports(imports, GetListIn.class, "lombok.Getter", "lombok.Setter");

            StringBuffer abuf = new StringBuffer();
            abuf.append(rn).append("@Getter");
            abuf.append(rn).append("@Setter");

            StringBuffer buf = new StringBuffer();

            Class<?> clazz = modelClass.getClazz();
            List<Field> fieldList = ReflectionUtils.getFieldList(clazz, false);
            for (Field field : fieldList) {
                String fieldName = field.getName();

                if ("factoryCode".equals(fieldName)) {
                    continue;
                }

                if (field.getAnnotation(DbistRelation.class) != null) {
                    continue;
                }

                if ("deleteFlag".equals(fieldName)) {
                    buf.append(rnt).append("private boolean includeDeleteFlag;");
                    continue;
                }

                Class<?> fieldType = field.getType();

                if ((fieldName.endsWith("Type") || fieldName.endsWith("Status")) && String.class.equals(fieldType)) {
                    buf.append(rnt).append("// private List<").append(fieldType.getSimpleName()).append("> ")
                                    .append(fieldName).append(";");
                    continue;
                }

                if (ZonedDateTime.class.equals(fieldType)) {
                    buf.append(rnt).append("// @JsonFormat(pattern = \"yyyy-MM-dd'T'HH:mm:ssZ\")");
                } else if (fieldName.endsWith("Date") && String.class.equals(fieldType)) {
                    buf.append(rnt).append("//@JsonDeserialize(using = DateStrDeserializer.class)");
                }
                buf.append(rnt).append("// private ").append(fieldType.getSimpleName()).append(" ").append(fieldName)
                                .append(";");
            }

            String src = SrcGenUtils.toClassSrc("Input VO", resource, false, getListInClass.toString(),
                            GetListIn.class.getName(), null, imports, abuf.toString(), buf.toString());
            getListInClass.setSrc(src);
        }

        Set<String> parentFields = new HashSet<>();
        {
            Class<?> clazz = modelClass.getClazz();
            List<Field> fieldList = ReflectionUtils.getFieldList(clazz, false);
            for (Field field : fieldList) {
                parentFields.add(field.getName());
            }
        }

        for (SubResource subRsc : subResources) {
            logger.info("\r\n" + ++i + ". Generating " + subRsc.vmodelClass + " Source\r\n");
            {

                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();
                buf.append(rnt).append("private String id;");

                Class<?> clazz = subRsc.modelClass.getClazz();
                List<Field> fieldList = ReflectionUtils.getFieldList(clazz, false);
                boolean passed = false;
                for (Field field : fieldList) {
                    if (!passed && parentFields.contains(field.getName())) {
                        continue;
                    }

                    passed = true;

                    if ("seqNo".equals(field.getName())) {
                        continue;
                    }
                    if (field.getAnnotation(DbistRelation.class) != null) {
                        continue;
                    }

                    Class<?> fieldType = field.getType();
                    SrcGenUtils.addImports(imports, fieldType);
                    if (ZonedDateTime.class.equals(fieldType)) {
                        SrcGenUtils.addImports(imports, JsonFormat.class);
                        buf.append(rnt).append("@JsonFormat(pattern = \"yyyy-MM-dd'T'HH:mm:ssZ\")");
                    } else if (field.getName().endsWith("Date") && String.class.equals(fieldType)) {
                        SrcGenUtils.addImports(imports, JsonSerialize.class);
                        SrcGenUtils.addImports(imports, DateStrSerializer.class);
                        buf.append(rnt).append("@JsonSerialize(using = DateStrSerializer.class)");
                        SrcGenUtils.addImports(imports, JsonDeserialize.class);
                        SrcGenUtils.addImports(imports, DateStrDeserializer.class);
                        buf.append(rnt).append("@JsonDeserialize(using = DateStrDeserializer.class)");
                    }
                    buf.append(rnt).append("private ").append(fieldType.getSimpleName()).append(" ")
                                    .append(field.getName()).append(";");
                }

                String src = SrcGenUtils.toClassSrc("Item VO", resource, false, subRsc.vmodelClass.toString(), null,
                                null, imports, abuf.toString(), buf.toString());
                subRsc.vmodelClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + subRsc.serviceClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, List.class, RestService4OneToMany.class, modelClass.toString(),
                                subRsc.modelClass.toString(), subRsc.vmodelClass.toString(), GetListOut.class);

                StringBuffer buf = new StringBuffer();
                buf.append(rnt).append("private RestService4OneToMany<").append(modelClass.getSimpleName()).append(", ")
                                .append(subRsc.modelClass.getSimpleName()).append(", ")
                                .append(subRsc.vmodelClass.getSimpleName())
                                .append("> restService = new RestService4OneToMany<>(")
                                .append(modelClass.getSimpleName()).append(".class, ")
                                .append(subRsc.modelClass.getSimpleName()).append(".class, ")
                                .append(subRsc.vmodelClass.getSimpleName()).append(".class);");
                buf.append(rn);

                buf.append(rnt).append("public GetListOut<").append(subRsc.vmodelClass.getSimpleName())
                                .append("> getList(String id) throws Exception {");
                buf.append(rnt2).append("return restService.getList(id);");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("public void saveList(String id, List<")
                                .append(subRsc.vmodelClass.getSimpleName())
                                .append("> list, String[] fieldName) throws Exception {");
                buf.append(rnt2).append("restService.saveList(id, list, fieldName);");
                buf.append(rnt).append("}");
                buf.append(rn);

                String src = SrcGenUtils.toClassSrc("Setup Service", resource, false, subRsc.serviceClass.toString(),
                                null, null, imports, null, buf.toString());
                subRsc.serviceClass.setSrc(src);
            }

        }

        for (ClassDef clazz : classes) {
            SrcGenUtils.write(clazz, overwrite);
        }
    }

    private static ClassDef scanModelClass(String pkg, String scanMsg) throws Exception {
        ClassDef modelClass;
        while (true) {
            String value = scan(scanMsg);
            if (value.contains(".")) {
                int index = value.lastIndexOf('.');
                String _pkg = value.substring(0, index);
                value = value.substring(index + 1);
                modelClass = new ClassDef(_pkg, value);
            } else {
                modelClass = new ClassDef(pkg, value);
            }
            try {
                Class<?> clazz = ClassUtils.getClass(modelClass.toString());
                modelClass.setClazz(clazz);
                break;
            } catch (Exception e) {
                logger.error(e.getMessage());
                continue;
            }
        }
        return modelClass;
    }

}
