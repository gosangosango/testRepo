package kr.co.miracom.framework.exception;

import java.util.Properties;

import kr.co.miracom.framework.util.Property;
import kr.co.miracom.framework.util.ValueUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public abstract class AbstractException extends RuntimeException {
    private String code;
    private String description;
    private Properties properties = new Properties();

    public AbstractException(String msgCode) {
        super(msgCode);
        this.code = msgCode;
    }

    public AbstractException(String msgCode, String message) {
        super(message);
        this.code = msgCode;
    }

    public AbstractException(String msgCode, Throwable cause) {
        super(msgCode, cause);
        this.code = msgCode;
    }

    public AbstractException(String msgCode, String message, Throwable cause) {
        super(message, cause);
        this.code = msgCode;
    }

    public AbstractException(String msgCode, Property... property) {
        super(msgCode);
        this.code = msgCode;
        addProperty(property);
    }

    public AbstractException(String msgCode, String message, Property... property) {
        super(message);
        this.code = msgCode;
        addProperty(property);
    }

    public AbstractException(String msgCode, String message, Properties properites) {
        super(message);
        this.code = msgCode;
        this.properties = properites;
    }

    public AbstractException(String msgCode, Throwable cause, Property... property) {
        super(msgCode, cause);
        this.code = msgCode;
        addProperty(property);
    }

    public AbstractException(String msgCode, String message, Throwable cause, Property... property) {
        super(message, cause);
        this.code = msgCode;
        addProperty(property);
    }

    public void addProperty(Property... property) {
        if (ValueUtils.isEmpty(property)) {
            return;
        }
        for (Property prop : property) {
            String key = prop.getName();
            String value = prop.getValue() == null ? "" : ValueUtils.toString(prop.getValue());
            properties.setProperty(key, value);
        }
    }
}
