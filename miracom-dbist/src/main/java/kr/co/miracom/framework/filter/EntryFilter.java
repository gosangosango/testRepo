package kr.co.miracom.framework.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import kr.co.miracom.framework.util.ValueUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class EntryFilter extends OncePerRequestFilter {
    private boolean headerLoggerEnabled;
    private boolean paramLoggerEnabled;
    private boolean sessionLoggerEnabled;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                    final FilterChain filterChain) throws ServletException, IOException {
        final long startTime = new Date().getTime();

        if (log.isInfoEnabled()) {
            StringBuffer buf = new StringBuffer("Request from ").append(getSource(request));
            buf.append(" to ").append(getTarget(request));
            if (headerLoggerEnabled) {
                buf.append("\r\nHeaders: ");
                @SuppressWarnings("rawtypes")
                Enumeration names = request.getHeaderNames();
                while (names.hasMoreElements()) {
                    String name = (String) names.nextElement();
                    buf.append("\r\n\t").append(name).append(": ");
                    @SuppressWarnings("rawtypes")
                    Enumeration headers = request.getHeaders(name);
                    List<String> headerList = new ArrayList<String>();
                    while (headers.hasMoreElements())
                        headerList.add((String) headers.nextElement());
                    if (!headerList.isEmpty()) {
                        if (headerList.size() == 1) {
                            buf.append(headerList.get(0));
                        } else {
                            int i = 0;
                            for (String value : headerList)
                                buf.append(i++ == 0 ? "" : ",").append("\r\n\t\t").append(value);
                        }
                    }
                }
            }
            if (paramLoggerEnabled) {
                buf.append("\r\nParameters: ");
                @SuppressWarnings("rawtypes")
                Enumeration names = request.getParameterNames();
                while (names.hasMoreElements()) {
                    String name = (String) names.nextElement();
                    buf.append("\r\n\t").append(name).append(": ");
                    String[] values = request.getParameterValues(name);
                    if (ValueUtils.isEmpty(values)) {
                        continue;
                    }
                    if (values.length == 1) {
                        buf.append(values[0]);
                    } else {
                        int i = 0;
                        for (String value : values)
                            buf.append(i++ == 0 ? "" : ",").append("\r\n\t\t").append(value);
                    }
                }
            }
            if (sessionLoggerEnabled) {
                HttpSession session = request.getSession(false);
                buf.append("\r\nSession: ");
                append(buf, session);
            }
            log.info(buf.toString());
        }

        try {
            filterChain.doFilter(request, response);

        } finally {
            if (log.isInfoEnabled()) {
                StringBuffer buf = new StringBuffer("Response (elapsedTime: ").append(new Date().getTime() - startTime);
                if (sessionLoggerEnabled) {
                    HttpSession session = request.getSession(false);
                    buf.append("\r\nSession: ");
                    append(buf, session);
                }
                log.info(buf.toString());
            }
        }
    }

    private static String getSource(HttpServletRequest request) {
        String remoteHost = request.getRemoteHost();
        String remoteAddr = request.getRemoteAddr();
        String referer = request.getHeader("Referer");
        return remoteHost
                        + (ValueUtils.isEmpty(remoteAddr) || remoteAddr.equals(remoteHost) ? ""
                                        : "(" + remoteAddr + ")")
                        + ":" + request.getRemotePort() + "/" + (ValueUtils.isEmpty(referer) ? "" : referer);
    }

    private String getTarget(HttpServletRequest request) {
        String target = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (!ValueUtils.isEmpty(queryString) && !target.contains("?"))
            target += "?" + queryString;
        return target;
    }

    private static StringBuffer append(StringBuffer buf, HttpSession session) {
        if (session == null) {
            buf.append("null");
            return buf;
        }
        buf.append("\r\n\tid: ").append(session.getId());
        buf.append("\r\n\tcreationTime: ").append(
                        ValueUtils.toDateStr(new Date(session.getCreationTime()), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        buf.append("\r\n\tlastAccessedTime: ").append(
                        ValueUtils.toDateStr(new Date(session.getLastAccessedTime()), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        buf.append("\r\n\tmaxInactiveInterval: ").append(session.getMaxInactiveInterval());
        @SuppressWarnings("unchecked")
        Enumeration<String> attrNames = session.getAttributeNames();
        buf.append("\r\n\tattributes: ");
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            buf.append("\r\n\t\t").append(name).append(": ").append(session.getAttribute(name));
        }
        return buf;
    }

}
