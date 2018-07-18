package kr.co.miracom.framework.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.util.StringUtils;

import kr.co.miracom.framework.tool.ClassDef;

public class SrcGenUtils {
    public static final String tab = "    ";

    /**
     * 클래스 소스로 바꿉니다.
     * @param type
     * @param id
     * @param name
     * @param extName
     * @param imports
     * @param source
     * @return
     * @throws Exception
     */
    public static String toClassSrc(String type, String id, boolean infFlag, String name, String extName,
                    List<String> infNames, List<String> imports, String typeAnn, String source) throws Exception {
        int index = name.lastIndexOf('.');
        String pkg = name.substring(0, index);
        String simpleName = name.substring(index + 1);

        if (imports == null)
            imports = new ArrayList<String>();

        boolean serializable = false;
        if (extName != null && extName.indexOf('.') > 0) {
            try {
                Class<?> extendsClass = ClassUtils.getClass(extName);
                serializable = Serializable.class.isAssignableFrom(extendsClass);
            } catch (Exception e) {
            }
            int lastIndex = extName.lastIndexOf('.');
            String extPkg = extName.substring(0, lastIndex);
            String extSimpleName = extName.substring(lastIndex + 1);
            if (!simpleName.equals(extSimpleName)) {
                if (!extPkg.equals(pkg)) {
                    addImports(imports, extName);
                }
                extName = extSimpleName;
            }
        }

        if (!ValueUtils.isEmpty(infNames)) {
            List<String> _infNames = new ArrayList<>(infNames.size());
            for (String infName : infNames) {
                int lastIndex = infName.lastIndexOf('.');
                String infPkg = infName.substring(0, lastIndex);
                String infSimpleName = infName.substring(lastIndex + 1);
                if (simpleName.equals(infSimpleName)) {
                    _infNames.add(infName);
                } else {
                    if (!infPkg.equals(pkg)) {
                        addImports(imports, infSimpleName);
                    }
                    _infNames.add(infSimpleName);
                }
                infNames = _infNames;
            }
        }

        Map<String, String> importMap = new TreeMap<String, String>();
        if (imports != null && !imports.isEmpty()) {
            for (String imp : imports)
                importMap.put(imp, imp);
        }

        StringBuffer buf = new StringBuffer("package ").append(pkg).append(";");
        buf.append("\r\n");

        int i = 0;
        String prevPrefix = null;
        for (String key : importMap.keySet()) {
            if (key.indexOf('.') == -1 || !key.startsWith("java.") || key.startsWith("java.lang."))
                continue;
            prevPrefix = "java";
            buf.append("\r\nimport ").append(key).append(";");
            i++;
        }
        for (String key : importMap.keySet()) {
            if (key.indexOf('.') == -1 || key.startsWith("java.") || pkg.equals(key.substring(0, key.lastIndexOf('.'))))
                continue;
            String prefix = key.substring(0, key.indexOf('.'));
            if (prevPrefix != null && !prevPrefix.endsWith(prefix))
                buf.append("\r\n");
            prevPrefix = prefix;
            buf.append("\r\nimport ").append(key).append(";");
            i++;
        }

        if (i != 0)
            buf.append("\r\n");
        buf.append("\r\n");
        appendComment(buf, type, id);
        if (!ValueUtils.isEmpty(typeAnn)) {
            if (!typeAnn.startsWith("\r\n") && !typeAnn.startsWith("\n")) {
                buf.append("\r\n");
            }
            buf.append(typeAnn);
        }
        if (serializable)
            buf.append("\r\n@SuppressWarnings(\"serial\")");
        buf.append("\r\npublic ").append(infFlag ? "interface " : "class ").append(simpleName);
        if (extName != null && extName.length() > 0)
            buf.append(" extends ").append(extName);
        if (!ValueUtils.isEmpty(infNames)) {
            buf.append(" implements ");
            int j = 0;
            for (String infName : infNames) {
                buf.append(j++ == 0 ? "" : ", ").append(infName);
            }
        }
        buf.append(" {");
        if (source == null || source.trim().length() == 0) {
            buf.append("\r\n");
        } else {
            if (!source.startsWith("\r\n") && !source.startsWith("\n"))
                buf.append("\r\n");
            buf.append(source);
        }
        buf.append("\r\n}\r\n");

        return buf.toString();
    }

    /**
     * class를 import 목록에 추가합니다.
     * @param imports
     * @param classes
     */
    public static void addImports(List<String> imports, Object... classes) {
        if (classes == null || classes.length == 0)
            return;
        for (Object clazz : classes) {
            String key = clazz instanceof Class ? ((Class<?>) clazz).getName() : clazz.toString();
            if (imports.contains(key))
                continue;
            imports.add(key);
        }
    }

    /**
     * Class에 대한 기본 Comment를 buf에 추가합니다.
     * @param buf
     * @param type
     * @param name
     */
    public static void appendComment(StringBuffer buf, String type, String name) {
        buf.append("/**");
        buf.append("\r\n * ").append(ValueUtils.toLabel(type)).append(": ").append(name);
        // buf.append("\r\n * ");
        try {
            String user = System.getProperty("user.name");
            if (!ValueUtils.isEmpty(user))
                buf.append("\r\n * @author ").append(user);
        } catch (Exception e) {
        }
        try {
            buf.append("\r\n * @since ").append(ValueUtils.toDateStr(new Date(), "yyyy. MM. dd."));
            // buf.append(" (version ").append(StringUtils.replace(version, "-SNAPSHOT", "")).append(")");
        } catch (Exception e) {
        }
        buf.append("\r\n */");
    }

    public static void write(ClassDef cls, boolean overwrite) throws Exception {
        if (cls == null)
            return;
        String path = "src/main/java/" + StringUtils.replace(cls.getPkg(), ".", "/");
        String fileName = path + "/" + cls.getSimpleName() + ".java";
        SrcGenUtils.write(fileName, cls.getSrc(), overwrite);
    }

    /**
     * 파일을 작성합니다.
     * @param path
     * @param str
     * @param overwrite
     * @throws IOException
     */
    public static void write(String path, String str, boolean overwrite) throws Exception {
        path = StringUtils.replace(path, "\\", "/");
        if (!SrcGenUtils.checkGenFilePath(path)) {
            return;
        }

        File file = new File(path);
        if (!overwrite && file.exists()) {
            System.out.println("File: " + file.getPath() + " already exists!!");
            return;
        }

        String dirPath = path.substring(0, path.lastIndexOf('/'));
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        } else if (!dir.isDirectory()) {
            System.out.println("Path: " + dir.getPath() + " is not a directory!!");
            return;
        }

        file = new File(path);
        FileUtils.writeStringToFile(file, str, "UTF-8", false);
    }

    /**
     * Generated file에 경로를 체크한다. Fortify - Path Manipulation 해결
     * @return
     */
    private static boolean checkGenFilePath(String filePath) {
        // if (filePath.contains("..") || (!filePath.startsWith("src/main") &&
        // !filePath.startsWith(Devs.getProjectPath() + "/src/main"))) {
        if (filePath.contains("..") || !filePath.startsWith("src/main")) {
            System.out.println("Invalid file path : " + filePath);
            return false;
        }
        return true;
    }

}
