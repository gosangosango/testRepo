package kr.co.miracom.mes.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.co.miracom.framework.filter.EntryFilter;
import kr.co.miracom.framework.util.BeanUtils;

@Configuration
public class AppConfig {

    // @Value("${miracom.entryFilter.parameterLogger.enabled}")
    private boolean paramLoggerEnabled = true;

    @Bean
    public BeanUtils beanUtils() {
        return new BeanUtils();
    }

    @Bean
    public FilterRegistrationBean entryFilter() {
        EntryFilter entryFilter = new EntryFilter();
        entryFilter.setParamLoggerEnabled(paramLoggerEnabled);
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(entryFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
