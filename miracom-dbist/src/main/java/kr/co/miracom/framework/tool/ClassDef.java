package kr.co.miracom.framework.tool;

import lombok.Data;

@Data
public class ClassDef {
    private String pkg;
    private String simpleName;
    private String src;
    private Class<?> clazz;

    public ClassDef(String pkg, String simpleName) {
        this.pkg = pkg;
        this.simpleName = simpleName;
    }

    public ClassDef(String pkg, String simpleName, String src) {
        this.pkg = pkg;
        this.simpleName = simpleName;
        this.src = src;
    }

    public String toString() {
        return pkg + "." + simpleName;
    }
}
