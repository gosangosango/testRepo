/**
 * Copyright (c) 1998-2018 Miracom Inc. All rights reserved.
 *
 * Don't copy or redistribute this source code without permission.
 * This software is provided "As Is" and any expressed or implied
 * warranties, including, but not limited to, the implied warranties of
 * merchantability and fitness for a particular purpose are disclaimed.
 * In no event shall Miracom Inc. or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential
 * damages including, but not limited to, procurement of substitute
 * goods or services; loss of use, data, or profits; or business
 * interruption) however caused and on any theory of liability, whether
 * in contract, strict liability, or tort (including negligence or otherwise)
 * arising in any way out of the use of this software, even if advised
 * of the possibility of such damage.
 *
 * For more information on this product, please see
 * http://www.miracom.co.kr
 */
package kr.co.miracom.framework.exception.handler;

import java.util.Date;
import java.util.Properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import kr.co.miracom.framework.exception.AbstractException;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.ValueUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractExceptionHandler {
    // TODO pkgs
    private static final String[] pkgs = { "kr.co.miracom" };

    protected ResponseEntity<ExceptionResponseBody> handle(HttpStatus status, AbstractException e, WebRequest request) {
        String code = e.getCode();
        // TODO check multi language message
        String description = e.getLocalizedMessage();
        String message = e.getLocalizedMessage();
        Properties properties = e.getProperties();
        boolean clientException = e instanceof BizException;
        Throwable t = e instanceof BizException || e.getCause() == null ? e : e.getCause();

        if (log.isErrorEnabled()) {
            StringBuffer buf = new StringBuffer("Error Message:");
            buf.append("\r\n\tstatus: ").append(status);
            buf.append("\r\n\tcode: ").append(code);
            buf.append("\r\n\tdescription: ").append(description);
            if (!ValueUtils.isEmpty(message) && !message.equals(description)) {
                buf.append("\r\n\tmessage: ").append(message);
            }
            buf.append("\r\n\tproperties: ").append(properties);

            buf.append("\r\n").append(e.getClass().getName()).append(": ").append(e.getMessage());
            for (StackTraceElement ste : t.getStackTrace()) {
                String className = ste.getClassName();
                if (className.contains("CGLIB$$")) {
                    continue;
                }
                boolean ourPkg = false;
                for (String pkg : pkgs) {
                    ourPkg = className.startsWith(pkg + ".");
                    if (ourPkg) {
                        break;
                    }
                }
                if (clientException && !ourPkg) {
                    continue;
                }

                boolean service = false;
                boolean util = false;
                if (ourPkg) {
                    service = (className.contains(".service.") && !className.startsWith("kr.co.miracom.service."));
                    util = !service && className.contains(".util.");
                }

                buf.append("\r\n\tat ");
                buf.append(className).append(".").append(ste.getMethodName());
                buf.append("(").append(ste.getFileName()).append(":").append(ste.getLineNumber()).append(")");
                if (service)
                    buf.append(" <<<============= The point of a service.");
                else if (util)
                    buf.append(" <<<============= The point of a util.");
            }
            buf.append("\r\n");
            log.error(buf.toString());
        }

        ExceptionResponseBody body = new ExceptionResponseBody(new Date(), status, code, description, properties);

        HttpHeaders headers = new HttpHeaders();
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<ExceptionResponseBody>(body, headers, status);
    }

}
