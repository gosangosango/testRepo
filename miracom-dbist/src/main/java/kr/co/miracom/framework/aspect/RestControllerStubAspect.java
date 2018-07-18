package kr.co.miracom.framework.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import kr.co.miracom.framework.exception.LogicException;
import kr.co.miracom.framework.util.CacheUtils;
import kr.co.miracom.framework.util.Closure;
import kr.co.miracom.framework.util.ReflectionUtils;
import kr.co.miracom.framework.util.RestUtils;
import kr.co.miracom.framework.util.SyncCtrlUtils;
import kr.co.miracom.framework.util.ValueUtils;
import lombok.Data;

public class RestControllerStubAspect {
    public Object doAround(final ProceedingJoinPoint point) throws Throwable {
        Signature sig = point.getSignature();

        Class<?> intf;
        {
            Class<?> stub = sig.getDeclaringType();
            intf = stub.getInterfaces()[0];
        }

        if (intf == null) {
            return point.proceed();
        }

        String methodName = sig.getName();
        Object[] args = point.getArgs();

        RestApi api = getApi(intf, methodName, args);

        if (api == null) {
            return point.proceed();
        }

        return call(api, args);
    }

    @Value("${miracom.msa.apiGatewayUrl}")
    private String apiGatewayUrl;

    private Object call(RestApi api, Object[] args) throws Exception {
        String url = apiGatewayUrl + api.getPath();
        String method = api.getMethod();
        Class<?> responseType = api.getReturnClass();

        // Path Vars
        Object[] pathVars;
        {
            List<Object> list = new ArrayList<>(api.getPathVars().size());
            for (int i : api.getPathVars().keySet()) {
                String value = args[i] == null ? "NONE" : args[i].toString();
                if ("NONE".equals(value)) {
                    return null;
                }
                // value = URLEncoder.encode(value, "UTF-8");
                list.add(value);
                // String name = "{" + param.getName() + "}";
                // String value = args[i] == null ? "NONE" : args[i].toString();
                // url = StringUtils.replace(url, name, value);
            }
            pathVars = list.toArray(new Object[list.size()]);
        }

        // URL
        {
            UriComponentsBuilder ub = UriComponentsBuilder.fromHttpUrl(url);
            if ("query".equals(api.getParamType())) {
                for (int i : api.getParams().keySet()) {
                    List<String> names = api.getParams().get(i);
                    if (names.size() == 1 && ValueUtils.isPrimitive(args[i])) {
                        if (args[i] == null) {
                            continue;
                        }
                        String value = ValueUtils.toString(args[i]);
                        // value = URLEncoder.encode(value, "UTF-8");
                        ub.queryParam(names.get(0), value);
                    } else {
                        Object target = args[i];
                        if (target == null) {
                            continue;
                        }
                        for (String name : names) {
                            String str = ValueUtils.getString(target, name);
                            if (ValueUtils.isEmpty(str)) {
                                continue;
                            }
                            ub.queryParam(name, str);
                        }
                    }
                }
            }
            UriComponents uri = ub.build();
            url = uri.toUriString();
        }

        Object ret = null;
        if ("GET".equals(method)) {
            ret = CacheUtils.get(url, pathVars, false);
            if (ret instanceof Exception) {
                throw (Exception) ret;
            }

            try {
                if (CacheUtils.isNull(ret)) {
                    ret = RestUtils.getRestOperations().getForObject(url, responseType, pathVars);
                    if (ret != null) {
                        CacheUtils.put(url, pathVars, ret, false);
                    }
                }
            } catch (Exception e) {
                CacheUtils.put(url, pathVars, e, false);
                throw e;
            }
        } else if ("POST".equals(method)) {
            // TODO myjung.jung
        } else if ("PUT".equals(method)) {
            // TODO myjung.jung
        } else if ("DELETE".equals(method)) {
            // TODO myjung.jung
        }
        return ret;
    }

    private static final Map<String, RestApi> APIS = new ConcurrentHashMap<>();

    private static RestApi getApi(Class<?> intf, String methodName, Object[] args) throws Exception {
        StringBuffer key = new StringBuffer(intf.getName()).append(",").append(methodName);

        RestApi api = SyncCtrlUtils.wrap("RestControllerStubAspect.getApi", APIS, key.toString(),
                        new Closure<RestApi, Exception>() {
                            @Override
                            public RestApi execute() throws Exception {
                                RestApi api = new RestApi();

                                Method m = null;
                                for (Method item : intf.getMethods()) {
                                    if (item.getName().equals(methodName) && item.getParameterCount() == args.length) {
                                        m = item;
                                        break;
                                    }
                                    continue;
                                }

                                if (m == null) {
                                    return api;
                                }

                                String path;
                                String method;
                                {
                                    StringBuffer buf = new StringBuffer();
                                    RequestMapping rm = intf.getAnnotation(RequestMapping.class);
                                    if (rm != null && rm.path().length > 0) {
                                        buf.append(rm.path()[0]);
                                    }

                                    if (m.getAnnotation(GetMapping.class) != null) {
                                        method = "GET";
                                        GetMapping get = m.getAnnotation(GetMapping.class);
                                        if (get.path().length > 0) {
                                            buf.append("/").append(get.path()[0]);
                                        }
                                    } else if (m.getAnnotation(PostMapping.class) != null) {
                                        method = "POST";
                                        PostMapping post = m.getAnnotation(PostMapping.class);
                                        if (post.path().length > 0) {
                                            buf.append("/").append(post.path()[0]);
                                        }
                                    } else if (m.getAnnotation(PutMapping.class) != null) {
                                        method = "PUT";
                                        PutMapping put = m.getAnnotation(PutMapping.class);
                                        if (put.path().length > 0) {
                                            buf.append("/").append(put.path()[0]);
                                        }
                                    } else if (m.getAnnotation(DeleteMapping.class) != null) {
                                        method = "DELETE";
                                        DeleteMapping delete = m.getAnnotation(DeleteMapping.class);
                                        if (delete.path().length > 0) {
                                            buf.append("/").append(delete.path()[0]);
                                        }
                                    } else {
                                        return api;
                                    }

                                    path = buf.toString();
                                }
                                api.setPath(path);
                                api.setMethod(method);

                                int i = 0;
                                for (Parameter p : m.getParameters()) {
                                    PathVariable pathVar = p.getAnnotation(PathVariable.class);
                                    if (pathVar != null) {
                                        RestParam param = new RestParam();
                                        // TODO myjung.jung
                                        param.setName("id");
                                        api.getPathVars().put(i++, param);
                                        continue;
                                    }

                                    RequestBody body = p.getAnnotation(RequestBody.class);
                                    if (body != null) {
                                        api.setBodyType(p.getParameterizedType());
                                        api.setBodyClass(p.getType());
                                        i++;
                                        continue;
                                    }

                                    RequestParam rp = p.getAnnotation(RequestParam.class);
                                    if (rp != null) {
                                        String name = rp.name().isEmpty() ? p.getName() : rp.name();
                                        api.getParams().put(i++, ValueUtils.toList(name));
                                        continue;
                                    }

                                    if (ValueUtils.isPrimitive(p.getType())) {
                                        api.getParams().put(i++, ValueUtils.toList(p.getName()));
                                        continue;
                                    }

                                    if (api.getBodyClass() == null) {
                                        if ("GET".equals(method)) {
                                            Class<?> type = p.getType();
                                            for (Field field : ReflectionUtils.getFieldList(type, true)) {
                                                List<String> names;
                                                if (api.getParams().containsKey(i)) {
                                                    names = api.getParams().get(i);
                                                } else {
                                                    names = new ArrayList<>();
                                                    api.getParams().put(i, names);
                                                }
                                                names.add(field.getName());
                                            }
                                            i++;
                                            continue;
                                        }

                                        api.setBodyType(p.getParameterizedType());
                                        api.setBodyClass(p.getType());
                                        i++;
                                        continue;
                                    }

                                    throw new LogicException("The REST Description is Not Enough for Call");
                                }

                                if (api.getBodyClass() != null && !"GET".equals(api.getMethod())) {
                                    api.setParamType("form");
                                }

                                api.setReturnType(m.getGenericReturnType());
                                api.setReturnClass(m.getReturnType());

                                return api;
                            }
                        });

        return api.getMethod() == null ? null : api;
    }

    @Data
    private static class RestApi {
        private String path;
        private String method;
        private Map<Integer, RestParam> pathVars = new LinkedHashMap<>(0);
        private Class<?> queryParamClass;
        private String paramType = "query";
        private Map<Integer, List<String>> params = new LinkedHashMap<>(0);
        private Type bodyType;
        private Class<?> bodyClass;
        private Type returnType;
        private Class<?> returnClass;
    }

    @Data
    private static class RestParam {
        private String name;
    }
}
