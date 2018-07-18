package kr.co.miracom.framework.exception;

import java.util.Properties;

import kr.co.miracom.framework.util.Property;

@SuppressWarnings("serial")
public class BizException extends AbstractException {

    public BizException(String msgCode) {
        super(msgCode);
    }

    public BizException(String msgCode, String message) {
        super(msgCode, message);
    }

    public BizException(String msgCode, Throwable cause) {
        super(msgCode, cause);
    }

    public BizException(String msgCode, String message, Throwable cause) {
        super(msgCode, message, cause);
    }

    public BizException(String msgCode, Property... property) {
        super(msgCode, property);
    }

    public BizException(String msgCode, String message, Property... property) {
        super(msgCode, message, property);
    }

    public BizException(String msgCode, String message, Properties properties) {
        super(msgCode, message, properties);
    }

    public BizException(String msgCode, Throwable cause, Property... property) {
        super(msgCode, cause, property);
    }

    public BizException(String msgCode, String message, Throwable cause, Property... property) {
        super(msgCode, message, cause, property);
    }

}
