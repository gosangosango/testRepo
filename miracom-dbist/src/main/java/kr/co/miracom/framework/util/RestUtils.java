package kr.co.miracom.framework.util;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.exception.LogicException;
import kr.co.miracom.framework.exception.SysException;
import kr.co.miracom.framework.exception.WarnException;
import kr.co.miracom.framework.exception.handler.ExceptionResponseBody;

public class RestUtils {

    private static RestOperations restOperations;

    public static RestOperations getRestOperations() {
        if (restOperations == null) {
            return _getRestOperations();
        }
        return restOperations;
    }

    private static synchronized RestOperations _getRestOperations() {
        if (restOperations != null) {
            return restOperations;
        }
        HttpComponentsClientHttpRequestFactory fac = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(fac);
        restTemplate.setMessageConverters(ValueUtils.toList(new MappingJackson2HttpMessageConverter()));
        restTemplate.setErrorHandler(new ErrorHandler());
        restOperations = restTemplate;
        return restTemplate;
    }

    private static ObjectMapper MAPPER = new ObjectMapper();

    private static class ErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            HttpStatus statusCode = getHttpStatusCode(response);
            byte[] bytes = getResponseBody(response);
            String str = new String(bytes, getCharset(response)).trim();

            if (!str.startsWith("{") || !str.endsWith("}")) {
                super.handleError(response);
                return;
            }

            ExceptionResponseBody body = MAPPER.readValue(str, ExceptionResponseBody.class);
            if (HttpStatus.BAD_REQUEST.equals(statusCode)) {
                throw new BizException(body.getCode(), body.getDescription(), body.getProperties());
            } else if (HttpStatus.PAYMENT_REQUIRED.equals(statusCode)) {
                throw new WarnException(body.getCode(), body.getDescription(), body.getProperties());
            } else if (HttpStatus.NOT_IMPLEMENTED.equals(statusCode)) {
                throw new LogicException(body.getDescription(), body.getProperties());
            } else if (HttpStatus.INTERNAL_SERVER_ERROR.equals(statusCode)) {
                throw new SysException(body.getDescription(), body.getProperties());
            }

            super.handleError(response);
        }
    }

}
