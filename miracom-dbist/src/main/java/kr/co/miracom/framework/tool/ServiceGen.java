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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.SrcGenUtils;
import kr.co.miracom.framework.util.ValueUtils;

/**
 * @author myjung.jung
 */
public class ServiceGen extends AbstractTool {
    private static final Logger logger = LoggerFactory.getLogger(ServiceGen.class);

    private static final String tab = SrcGenUtils.tab;
    private static final String rn = "\r\n";
    private static final String rnt = rn + tab;
    private static final String rnt2 = rn + tab + tab;

    public static void main(String[] args) {
        try {
            gen(args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Good-bye!");
        }
    }

    public static void gen(String[] args) throws Exception {
        String pkg = scan("package");
        String prodPkg;
        String prodName;
        String module;
        {
            prodPkg = pkg.substring(0, pkg.lastIndexOf('.'));
            prodName = prodPkg.substring(prodPkg.lastIndexOf('.') + 1);
            module = pkg.substring(pkg.lastIndexOf('.') + 1);
        }

        String resource = StringUtils.capitalize(scan("resource"));
        String usRsc = ValueUtils.toDelimited(resource, '_');
        boolean simple = ValueUtils.toBoolean(scan("simple?"));
        String pkgPrefix = pkg + ".resource." + (simple ? "simple." : "complex.") + usRsc;

        List<ClassDef> classes = new ArrayList<>();
        ClassDef ctrlInfClass = new ClassDef(pkgPrefix + ".controller", resource + "Controller");
        classes.add(ctrlInfClass);
        ClassDef ctrlImplClass = new ClassDef(pkgPrefix + ".controller", resource + "ControllerImpl");
        classes.add(ctrlImplClass);
        ClassDef ctrlStubClass = new ClassDef(pkgPrefix + ".controller", resource + "ControllerStub");
        classes.add(ctrlStubClass);

        String rscPl;

        boolean overwrite;

        if (simple) {
            rscPl = StringUtils.uncapitalize(scan("resource-pl"));
            String action = StringUtils.uncapitalize(scan("action"));
            String usAct = ValueUtils.toDelimited(action, '_');
            String simpleName;
            {
                StringBuffer buf = new StringBuffer();
                int i = 0;
                for (String str : StringUtils.tokenizeToStringArray(usAct, "_")) {
                    str = StringUtils.capitalize(str);
                    buf.append(str);
                    if (i++ == 0) {
                        buf.append(resource);
                    }
                }
                simpleName = buf.toString();
            }
            overwrite = ValueUtils.toBoolean(scan("overwrite?"));

            // ClassDef actClass = new ClassDef(pkgPrefix + ".service." + usAct, simpleName);
            // classes.add(actClass);
            // ClassDef actInClass = new ClassDef(pkgPrefix + ".service." + usAct, simpleName + "In");
            // classes.add(actInClass);
            // ClassDef actOutClass = new ClassDef(pkgPrefix + ".service." + usAct, simpleName + "Out");
            // classes.add(actOutClass);

            {
                StringBuffer buf = new StringBuffer();
                buf.append("\r\nStart Generating Classes:");
                for (ClassDef clazz : classes) {
                    buf.append("\r\n\t").append(clazz);
                }
                buf.append("\r\n");
                logger.info(buf.toString());
            }

            // TODO myjung.jung

        } else {
            rscPl = StringUtils.uncapitalize(resource);
            overwrite = ValueUtils.toBoolean(scan("overwrite?"));

            ClassDef getClass = new ClassDef(pkgPrefix + ".service.get", "Get" + resource);
            classes.add(getClass);
            ClassDef getInClass = new ClassDef(pkgPrefix + ".service.get", "Get" + resource + "In");
            classes.add(getInClass);
            ClassDef getOutClass = new ClassDef(pkgPrefix + ".service.get", "Get" + resource + "Out");
            classes.add(getOutClass);
            ClassDef postClass = new ClassDef(pkgPrefix + ".service.post", "Post" + resource);
            classes.add(postClass);
            ClassDef postInClass = new ClassDef(pkgPrefix + ".service.post", "Post" + resource + "In");
            classes.add(postInClass);
            ClassDef postOutClass = new ClassDef(pkgPrefix + ".service.post", "Post" + resource + "Out");
            classes.add(postOutClass);

            ClassDef getListClass = new ClassDef(pkgPrefix + ".service.get_list", "Get" + resource + "List");
            classes.add(getListClass);
            ClassDef getListInClass = new ClassDef(pkgPrefix + ".service.get_list", "Get" + resource + "ListIn");
            classes.add(getListInClass);
            ClassDef getListOutClass = new ClassDef(pkgPrefix + ".service.get_list", "Get" + resource + "ListOut");
            classes.add(getListOutClass);
            ClassDef postListClass = new ClassDef(pkgPrefix + ".service.post_list", "Post" + resource + "List");
            classes.add(postListClass);
            ClassDef postListInClass = new ClassDef(pkgPrefix + ".service.post_list", "Post" + resource + "ListIn");
            classes.add(postListInClass);
            ClassDef postListOutClass = new ClassDef(pkgPrefix + ".service.post_list", "Post" + resource + "ListOut");
            classes.add(postListOutClass);

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
                SrcGenUtils.addImports(imports, PathVariable.class, RequestBody.class, RequestMapping.class, Api.class,
                                ApiImplicitParam.class, ApiImplicitParams.class, ApiOperation.class,
                                prodPkg + ".config." + StringUtils.capitalize(prodName) + "Const",
                                getInClass.toString(), getOutClass.toString(), postInClass.toString(),
                                postOutClass.toString(), getListInClass.toString(), getListOutClass.toString(),
                                postListInClass.toString(), postListOutClass.toString());

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Api(value = \"Processing ").append(StringUtils.capitalize(rscPl))
                                .append(".\")");
                abuf.append(rn).append("@RequestMapping(path = ").append(StringUtils.capitalize(prodName))
                                .append("Const.VER_PATH").append(")");

                StringBuffer buf = new StringBuffer();
                buf.append(rnt).append("static final String RESOURCE = \"").append(module).append("/").append(rscPl)
                                .append("\";");
                buf.append(rn);

                buf.append(rnt).append("// @GetMapping(path = RESOURCE + \"/{id}\")");
                buf.append(rnt).append("@ApiOperation(\"Get ").append(resource).append("\")");
                buf.append(rnt).append(
                                "@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"ID\", required = true) })");
                buf.append(rnt).append(getOutClass.getSimpleName()).append(" get(@PathVariable String id, ")
                                .append(getInClass.getSimpleName()).append(" input) throws Exception;");
                buf.append(rn);

                buf.append(rnt).append("// @PostMapping(path = RESOURCE + \"/{id}\")");
                buf.append(rnt).append("@ApiOperation(\"Post ").append(resource).append("\")");
                buf.append(rnt).append(
                                "@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"ID\", required = true) })");
                buf.append(rnt).append(postOutClass.getSimpleName())
                                .append(" post(@PathVariable String id, @RequestBody ")
                                .append(postInClass.getSimpleName()).append(" input) throws Exception;");
                buf.append(rn);

                buf.append(rnt).append("// @GetMapping(path = RESOURCE)");
                buf.append(rnt).append("@ApiOperation(\"Get ").append(resource).append("\")");
                buf.append(rnt).append(getListOutClass.getSimpleName()).append(" getList(")
                                .append(getListInClass.getSimpleName()).append(" input) throws Exception;");
                buf.append(rn);

                buf.append(rnt).append("// @PostMapping(path = RESOURCE)");
                buf.append(rnt).append("@ApiOperation(\"Post ").append(resource).append("\")");
                buf.append(rnt).append(postListOutClass.getSimpleName()).append(" postList(@RequestBody ")
                                .append(postListInClass.getSimpleName()).append(" input) throws Exception;");
                buf.append(rn);

                String src = SrcGenUtils.toClassSrc("Service Controller", resource, true, ctrlInfClass.toString(), null,
                                null, imports, abuf.toString(), buf.toString());
                ctrlInfClass.setSrc(src);

            }

            logger.info("\r\n" + ++i + ". Generating " + ctrlImplClass + " Source\r\n");
            {
                List<String> infs = new ArrayList<>();
                infs.add(ctrlInfClass.toString());

                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, CrossOrigin.class, PathVariable.class, RequestBody.class,
                                RestController.class, BeanUtils.class, getClass.toString(), getInClass.toString(),
                                getOutClass.toString(), postClass.toString(), postInClass.toString(),
                                postOutClass.toString(), getListClass.toString(), getListInClass.toString(),
                                getListOutClass.toString(), postListClass.toString(), postListInClass.toString(),
                                postListOutClass.toString());

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@CrossOrigin");
                abuf.append(rn).append("@RestController");

                StringBuffer buf = new StringBuffer();
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(getOutClass.getSimpleName())
                                .append(" get(@PathVariable String id, ").append(getInClass.getSimpleName())
                                .append(" input) throws Exception {");
                buf.append(rnt2).append("input.setId(id);");
                buf.append(rnt2).append("return BeanUtils.get(").append(getClass.getSimpleName())
                                .append(".class).get(input);");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(postOutClass.getSimpleName())
                                .append(" post(@PathVariable String id, @RequestBody ")
                                .append(postInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append("input.setId(id);");
                buf.append(rnt2).append("return BeanUtils.get(").append(postClass.getSimpleName())
                                .append(".class).post(input);");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(getListOutClass.getSimpleName()).append(" getList(")
                                .append(getListInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append("return BeanUtils.get(").append(getListClass.getSimpleName())
                                .append(".class).getList(input);");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(postListOutClass.getSimpleName())
                                .append(" postList(@RequestBody ").append(postListInClass.getSimpleName())
                                .append(" input) throws Exception {");
                buf.append(rnt2).append("return BeanUtils.get(").append(postListClass.getSimpleName())
                                .append(".class).postList(input);");
                buf.append(rnt).append("}");
                buf.append(rn);

                String src = SrcGenUtils.toClassSrc("Service Controller", resource, false, ctrlImplClass.toString(),
                                null, infs, imports, abuf.toString(), buf.toString());
                ctrlImplClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + ctrlStubClass + " Source\r\n");
            {
                List<String> infs = new ArrayList<>();
                infs.add(ctrlInfClass.toString());

                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, getInClass.toString(), getOutClass.toString(), postInClass.toString(),
                                postOutClass.toString(), getListInClass.toString(), getListOutClass.toString(),
                                postListInClass.toString(), postListOutClass.toString());

                StringBuffer buf = new StringBuffer();
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(getOutClass.getSimpleName()).append(" get(String id, ")
                                .append(getInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append("return null;");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(postOutClass.getSimpleName()).append(" post(String id, ")
                                .append(postInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append("return null;");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(getListOutClass.getSimpleName()).append(" getList(")
                                .append(getListInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append("return null;");
                buf.append(rnt).append("}");
                buf.append(rn);

                buf.append(rnt).append("@Override");
                buf.append(rnt).append("public ").append(postListOutClass.getSimpleName()).append(" postList(")
                                .append(postListInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append("return null;");
                buf.append(rnt).append("}");
                buf.append(rn);

                String src = SrcGenUtils.toClassSrc("Service Controller", resource, false, ctrlStubClass.toString(),
                                null, infs, imports, null, buf.toString());
                ctrlStubClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + getClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                // SrcGenUtils.addImports(imports);

                StringBuffer buf = new StringBuffer();
                buf.append(rnt).append("public ").append(getOutClass.getSimpleName()).append(" get(")
                                .append(getInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append(getOutClass.getSimpleName()).append(" output = new ")
                                .append(getOutClass.getSimpleName()).append("();");
                buf.append(rnt2).append("return output;");
                buf.append(rnt).append("}");

                String src = SrcGenUtils.toClassSrc("Get Service", resource, false, getClass.toString(), null, null,
                                imports, null, buf.toString());
                getClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + getInClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                buf.append(rnt).append("private String id;");

                String src = SrcGenUtils.toClassSrc("Input VO", resource, false, getInClass.toString(), null, null,
                                imports, abuf.toString(), buf.toString());
                getInClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + getOutClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                buf.append(rnt).append("private boolean success = true;");

                String src = SrcGenUtils.toClassSrc("Output VO", resource, false, getOutClass.toString(), null, null,
                                imports, abuf.toString(), buf.toString());
                getOutClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + postClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                // SrcGenUtils.addImports(imports);

                StringBuffer buf = new StringBuffer();
                buf.append(rnt).append("public ").append(postOutClass.getSimpleName()).append(" post(")
                                .append(postInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append(postOutClass.getSimpleName()).append(" output = new ")
                                .append(postOutClass.getSimpleName()).append("();");
                buf.append(rnt2).append("return output;");
                buf.append(rnt).append("}");

                String src = SrcGenUtils.toClassSrc("Post Service", resource, false, postClass.toString(), null, null,
                                imports, null, buf.toString());
                postClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + postInClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                buf.append(rnt).append("private String id;");

                String src = SrcGenUtils.toClassSrc("Input VO", resource, false, postInClass.toString(), null, null,
                                imports, abuf.toString(), buf.toString());
                postInClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + postOutClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                buf.append(rnt).append("private boolean success = true;");

                String src = SrcGenUtils.toClassSrc("Output VO", resource, false, postOutClass.toString(), null, null,
                                imports, abuf.toString(), buf.toString());
                postOutClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + getListClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                // SrcGenUtils.addImports(imports);

                StringBuffer buf = new StringBuffer();
                buf.append(rnt).append("public ").append(getListOutClass.getSimpleName()).append(" getList(")
                                .append(getListInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append(getListOutClass.getSimpleName()).append(" output = new ")
                                .append(getListOutClass.getSimpleName()).append("();");
                buf.append(rnt2).append("return output;");
                buf.append(rnt).append("}");

                String src = SrcGenUtils.toClassSrc("Get List Service", resource, false, getListClass.toString(), null,
                                null, imports, null, buf.toString());
                getListClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + getListInClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                String src = SrcGenUtils.toClassSrc("Input VO", resource, false, getListInClass.toString(), null, null,
                                imports, abuf.toString(), buf.toString());
                getListInClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + getListOutClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                buf.append(rnt).append("private boolean success = true;");

                String src = SrcGenUtils.toClassSrc("Output VO", resource, false, getListOutClass.toString(), null,
                                null, imports, abuf.toString(), buf.toString());
                getListOutClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + postListClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                // SrcGenUtils.addImports(imports);

                StringBuffer buf = new StringBuffer();
                buf.append(rnt).append("public ").append(postListOutClass.getSimpleName()).append(" postList(")
                                .append(postListInClass.getSimpleName()).append(" input) throws Exception {");
                buf.append(rnt2).append(postListOutClass.getSimpleName()).append(" output = new ")
                                .append(postListOutClass.getSimpleName()).append("();");
                buf.append(rnt2).append("return output;");
                buf.append(rnt).append("}");

                String src = SrcGenUtils.toClassSrc("Post List Service", resource, false, postListClass.toString(),
                                null, null, imports, null, buf.toString());
                postListClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + postListInClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                String src = SrcGenUtils.toClassSrc("Input VO", resource, false, postListInClass.toString(), null, null,
                                imports, abuf.toString(), buf.toString());
                postListInClass.setSrc(src);
            }

            logger.info("\r\n" + ++i + ". Generating " + postListOutClass + " Source\r\n");
            {
                List<String> imports = new ArrayList<String>();
                SrcGenUtils.addImports(imports, "lombok.Data");

                StringBuffer abuf = new StringBuffer();
                abuf.append(rn).append("@Data");

                StringBuffer buf = new StringBuffer();

                buf.append(rnt).append("private boolean success = true;");

                String src = SrcGenUtils.toClassSrc("Output VO", resource, false, postListOutClass.toString(), null,
                                null, imports, abuf.toString(), buf.toString());
                postListOutClass.setSrc(src);
            }
        }

        for (ClassDef clazz : classes) {
            SrcGenUtils.write(clazz, overwrite);
        }
    }

}
