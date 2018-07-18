package kr.co.miracom.framework.util;

import lombok.Data;

@Data
public class Property {
    private String name;
    private Object value;

    public Property() {
        super();
    }

    public Property(String name, Object value) {
        super();
        setName(name);
        setValue(value);
    }
}
