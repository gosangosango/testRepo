package kr.co.miracom.framework.exception;

import java.util.Properties;

import kr.co.miracom.framework.util.Property;

@SuppressWarnings("serial")
public class WarnException extends BizException {

    public WarnException(String msgCode) {
        super(msgCode);
    }

    public WarnException(String msgCode, String message) {
        super(msgCode, message);
    }

    public WarnException(String msgCode, Throwable cause) {
        super(msgCode, cause);
    }

    public WarnException(String msgCode, String message, Throwable cause) {
        super(msgCode, message, cause);
    }

    public WarnException(String msgCode, Property... property) {
        super(msgCode, property);
    }

    public WarnException(String msgCode, String message, Property... property) {
        super(msgCode, message, property);
    }

    public WarnException(String msgCode, String message, Properties properties) {
        super(msgCode, message, properties);
    }

    public WarnException(String msgCode, Throwable cause, Property... property) {
        super(msgCode, cause, property);
    }

    public WarnException(String msgCode, String message, Throwable cause, Property... property) {
        super(msgCode, message, cause, property);
    }

}
