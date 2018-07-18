package kr.co.miracom.framework.exception;

import java.util.Properties;

import kr.co.miracom.framework.util.Property;

@SuppressWarnings("serial")
public class SysException extends AbstractException {
    public static final String CODE = "SYSTEM";

    public SysException() {
        super(CODE);
    }

    public SysException(String message) {
        super(CODE, message);
    }

    public SysException(Throwable cause) {
        super(CODE, cause);
    }

    public SysException(String message, Throwable cause) {
        super(CODE, message, cause);
    }

    public SysException(Property... property) {
        super(CODE, property);
    }

    public SysException(String message, Property... property) {
        super(CODE, message, property);
    }

    public SysException(String message, Properties properties) {
        super(CODE, message, properties);
    }

    public SysException(Throwable cause, Property... property) {
        super(CODE, cause, property);
    }

    public SysException(String message, Throwable cause, Property... property) {
        super(CODE, message, cause, property);
    }

}
