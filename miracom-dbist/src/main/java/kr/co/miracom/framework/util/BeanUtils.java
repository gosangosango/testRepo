package kr.co.miracom.framework.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class BeanUtils implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    private static BeanUtils beans;

    public BeanUtils() {
        beans = this;
    }

    public static ApplicationContext getContext() {
        return beans.getApplicationContext();
    }

    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() throws BeansException {
        return this.applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private static Map<Class<?>, Object> beanByClassCache = new ConcurrentHashMap<Class<?>, Object>();

    /**
     * Get a singletone spring bean by requiredType.<br>
     * If the bean does not exist, it'll be created and registered.
     * 
     * <pre>
     * beanUtils.get(TestService.class).execute(vo);
     * </pre>
     * 
     * @param clazz
     * @return A singletone spring bean
     */
    public static <T> T get(Class<T> clazz) {
        return get(clazz, clazz);
    }

    /**
     * Get a singletone spring bean by clazz and requiredType.<br>
     * If the bean does not exist, it'll be created and registered.
     * @param clazz
     * @param requiredType
     * @return A singletone spring bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Class<?> clazz, final Class<T> requiredType) {
        if (clazz == null)
            throw new IllegalArgumentException("clazz is required.");
        if (requiredType == null)
            throw new IllegalArgumentException("requiredType is required.");

        T bean;
        if (beanByClassCache.containsKey(clazz)) {
            bean = (T) beanByClassCache.get(clazz);
        } else {
            synchronized (clazz) {
                if (clazz.isInterface() && clazz.equals(requiredType)) {
                    try {
                        clazz = ClassUtils.getClass(clazz.getName() + "Impl");
                    } catch (Exception e) {
                        try {
                            clazz = ClassUtils.getClass(clazz.getName() + "Stub");
                        } catch (Exception e1) {
                        }
                    }
                }
                try {
                    bean = (T) getContext().getBean(clazz);
                } catch (BeansException e) {
                    try {
                        bean = getContext().getBean(clazz.getName(), requiredType);
                    } catch (NoSuchBeanDefinitionException e1) {
                        beans.register(clazz);
                        bean = getContext().getBean(clazz.getName(), requiredType);
                    }
                }
                synchronized (clazz) {
                    beanByClassCache.put(clazz, bean);
                }
            }
        }

        if (!requiredType.isAssignableFrom(bean.getClass()))
            throw new BeanNotOfRequiredTypeException(bean.getClass().getName(), requiredType, bean.getClass());
        return bean;
    }

    private static final Map<String, Object> BEANS = new ConcurrentHashMap<String, Object>();

    /**
     * Get a singletone spring bean by name.
     * 
     * <pre>
     * ((TestService) beanUtils.get(&quot;testService&quot;)).execute(vo);
     * </pre>
     * 
     * @param name
     * @return A singletone spring bean
     */
    public static Object get(final String name) {
        ValueUtils.assertNotNull("name", name);

        final boolean debug = logger.isDebugEnabled();

        if (BEANS.containsKey(name)) {
            if (debug)
                logger.debug("get bean from beanByNameCache by name: " + name);
            return BEANS.get(name);
        }
        return SyncCtrlUtils.wrap("BeanUtils.BEANS." + name, BEANS, name, new Closure<Object, RuntimeException>() {
            public Object execute() {
                if (debug)
                    logger.debug("try to get bean from applicationContext by name: " + name);
                return getContext().getBean(name);
            }
        });
    }

    /**
     * Get a singletone spring bean by name and requiredType.
     * 
     * <pre>
     * beanUtils.get(&quot;testService&quot;, TestService.class).execute(vo);
     * </pre>
     * 
     * @param name
     * @param requiredType
     * @return A singletone spring bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(final String name, final Class<T> requiredType) {
        ValueUtils.assertNotNull("name", name);
        ValueUtils.assertNotNull("requiredType", requiredType);

        Object bean = get(name);

        if (!requiredType.isAssignableFrom(bean.getClass()))
            throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
        return (T) bean;
    }

    public static class BeanRef {
        public BeanRef(String name) {
            setName(name);
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private <T> void register(Class<T> clazz) {
        register(clazz.getName(), clazz, null);
    }

    public static <T> void register(String name, Class<T> clazz) {
        register(name, clazz, false, null, null, null, null, null);
    }

    public static <T> void register(String name, Class<T> clazz, List<Property> props) {
        register(name, clazz, false, null, props, null, null, null);
    }

    public static <T> void register(String name, Class<T> clazz, boolean lazyInit, List<Object> constArgs,
                    List<Property> props, String initMethodName, String destroyMethodName, List<String> dependsOn) {
        BeanDefinitionRegistry bdr;
        BeanDefinitionBuilder bdb;
        if (BeanUtils.getContext() instanceof AbstractRefreshableConfigApplicationContext) {
            bdb = BeanDefinitionBuilder.rootBeanDefinition(clazz);
            AbstractRefreshableConfigApplicationContext ac = (AbstractRefreshableConfigApplicationContext) BeanUtils
                            .getContext();
            bdr = (DefaultListableBeanFactory) ac.getBeanFactory();
        } else if (BeanUtils.getContext() instanceof GenericApplicationContext) {
            bdb = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            bdr = (GenericApplicationContext) BeanUtils.getContext();
        } else {
            throw new NoSuchBeanDefinitionException(clazz);
        }

        bdb.setLazyInit(lazyInit);

        if (!ValueUtils.isEmpty(initMethodName))
            bdb.setInitMethodName(initMethodName);
        if (!ValueUtils.isEmpty(destroyMethodName))
            bdb.setDestroyMethodName(destroyMethodName);
        if (!ValueUtils.isEmpty(dependsOn)) {
            for (String beanName : dependsOn)
                bdb.addDependsOn(beanName);
        }
        if (!ValueUtils.isEmpty(constArgs)) {
            for (Object constArg : constArgs) {
                if (constArg instanceof BeanRef) {
                    bdb.addConstructorArgReference(((BeanRef) constArg).getName());
                } else {
                    bdb.addConstructorArgValue(constArg);
                }
            }
        }
        if (!ValueUtils.isEmpty(props)) {
            for (Property prop : props) {
                if (prop.getValue() instanceof BeanRef) {
                    bdb.addPropertyReference(prop.getName(), ((BeanRef) prop.getValue()).getName());
                } else {
                    bdb.addPropertyValue(prop.getName(), prop.getValue());
                }
            }
        }

        BeanDefinition bd = bdb.getBeanDefinition();
        bdr.registerBeanDefinition(name, bd);
    }

    public static void unregisterQuietly(String name) throws Exception {
        try {
            unregister(name);
        } catch (Exception e1) {
        }
    }

    public static <T> void unregister(String name) {
        BeanDefinitionRegistry bdr;
        if (BeanUtils.getContext() instanceof AbstractRefreshableConfigApplicationContext) {
            AbstractRefreshableConfigApplicationContext ac = (AbstractRefreshableConfigApplicationContext) BeanUtils
                            .getContext();
            bdr = (DefaultListableBeanFactory) ac.getBeanFactory();
        } else if (BeanUtils.getContext() instanceof GenericApplicationContext) {
            bdr = (GenericApplicationContext) BeanUtils.getContext();
        } else {
            throw new NoSuchBeanDefinitionException(name);
        }
        bdr.removeBeanDefinition(name);
    }
}
