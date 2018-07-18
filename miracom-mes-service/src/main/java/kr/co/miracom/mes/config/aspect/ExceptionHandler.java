package kr.co.miracom.mes.config.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;

import kr.co.miracom.framework.exception.handler.MessageExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends MessageExceptionHandler {

}
