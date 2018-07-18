package kr.co.miracom.framework.exception;

import java.util.Properties;

import kr.co.miracom.framework.util.Property;

@SuppressWarnings("serial")
public class LogicException extends AbstractException {
    public static final String CODE = "LOGIC";

    public LogicException() {
        super(CODE);
    }

    public LogicException(String message) {
        super(CODE, message);
    }

    public LogicException(Throwable cause) {
        super(CODE, cause);
    }

    public LogicException(String message, Throwable cause) {
        super(CODE, message, cause);
    }

    public LogicException(Property... property) {
        super(CODE, property);
    }

    public LogicException(String message, Property... property) {
        super(CODE, message, property);
    }

    public LogicException(String message, Properties properties) {
        super(CODE, message, properties);
    }

    public LogicException(Throwable cause, Property... property) {
        super(CODE, cause, property);
    }

    public LogicException(String message, Throwable cause, Property... property) {
        super(CODE, message, cause, property);
    }

}
