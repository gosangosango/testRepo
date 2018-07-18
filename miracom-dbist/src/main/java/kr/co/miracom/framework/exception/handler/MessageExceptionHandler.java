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

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.velocity.exception.ParseErrorException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;

import kr.co.miracom.dbist.exception.DbistRuntimeException;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.exception.LogicException;
import kr.co.miracom.framework.exception.SysException;
import kr.co.miracom.framework.exception.WarnException;
import kr.co.miracom.framework.util.ValueUtils;

public class MessageExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(InvocationTargetException.class)
    public final ResponseEntity<ExceptionResponseBody> handleException(InvocationTargetException e,
                    WebRequest request) {
        Throwable t = e.getTargetException();
        if (t == null) {
            t = e;
        }
        if (T.contains(t.getClass())) {
            return handleLogicException(t, request);
        }
        return handleSysException(t, request);
    }

    @SuppressWarnings("unchecked")
    private static final Set<Class<? extends Throwable>> T = ValueUtils.toSet(NullPointerException.class,
                    IllegalArgumentException.class, ArrayIndexOutOfBoundsException.class, ParseErrorException.class,
                    DbistRuntimeException.class, BadSqlGrammarException.class, InvalidDataAccessApiUsageException.class,
                    JsonParseException.class);

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class,
                    ArrayIndexOutOfBoundsException.class, ParseErrorException.class, DbistRuntimeException.class,
                    BadSqlGrammarException.class, InvalidDataAccessApiUsageException.class, JsonParseException.class })
    public final ResponseEntity<ExceptionResponseBody> handleLogicException(Throwable e, WebRequest request) {
        return handleLogicException(new LogicException(e.getMessage(), e), request);
    }

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<ExceptionResponseBody> handleSysException(Throwable t, WebRequest request) {
        return handleSysException(new SysException(t.getMessage(), t), request);
    }

    @ExceptionHandler(LogicException.class)
    public final ResponseEntity<ExceptionResponseBody> handleLogicException(LogicException e, WebRequest request) {
        ResponseEntity<ExceptionResponseBody> resp = handle(HttpStatus.NOT_IMPLEMENTED, e, request);
        return resp;
    }

    @ExceptionHandler(SysException.class)
    public final ResponseEntity<ExceptionResponseBody> handleSysException(SysException e, WebRequest request) {
        ResponseEntity<ExceptionResponseBody> resp = handle(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
        return resp;
    }

    @ExceptionHandler(BizException.class)
    public final ResponseEntity<ExceptionResponseBody> handleBizException(BizException e, WebRequest request) {
        ResponseEntity<ExceptionResponseBody> resp = handle(HttpStatus.BAD_REQUEST, e, request);
        return resp;
    }

    @ExceptionHandler(WarnException.class)
    public final ResponseEntity<ExceptionResponseBody> handleWarningException(WarnException e, WebRequest request) {
        ResponseEntity<ExceptionResponseBody> resp = handle(HttpStatus.PAYMENT_REQUIRED, e, request);
        return resp;
    }

}
