package kr.co.miracom.mes.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import kr.co.miracom.dbist.dml.Dml;
import kr.co.miracom.dbist.dml.impl.DmlJdbc;
import kr.co.miracom.dbist.dml.jdbc.QueryMapper;
import kr.co.miracom.dbist.processor.impl.VelocityPreprocessor;
import kr.co.miracom.framework.transaction.AnnotationTransactionAttributeSource;

@Configuration
public class DataSourceConfig {

    @Autowired()
    private DataSource dataSource;

    @Value("${miracom.dml.domain}")
    private String domain;

    @Value("${miracom.dml.columnAliasRuleForMapKey}")
    private String columnAliasRuleForMapKey;

    @Value("${miracom.dml.maxSqlByPathCacheSize}")
    private int maxSqlByPathCacheSize;

    @Value("${miracom.dml.defaultLockTimeout}")
    private int defaultLockTimeout;

    @Value("${miracom.dml.queryMapper}")
    private Class<?> queryMapper;

    @Bean
    public Dml dml() throws Exception {
        DmlJdbc bean = new DmlJdbc();
        bean.setDataSource(dataSource);
        bean.setDomain(domain);
        bean.setColumnAliasRuleForMapKey(columnAliasRuleForMapKey);
        bean.setJdbcOperations(jdbcOperations());
        bean.setNamedParameterJdbcOperations(namedParameterJdbcOperations());
        bean.setMaxSqlByPathCacheSize(maxSqlByPathCacheSize);
        bean.setDefaultLockTimeout(defaultLockTimeout);
        bean.setPreprocessor(new VelocityPreprocessor());
        bean.setQueryMapper((QueryMapper) queryMapper.newInstance());
        bean.getQueryMapper().setEscapeAllWords(false);
        bean.setReservedWordTolerated(false);
        return bean;
    }

    @Value("${miracom.jdbcOperations.fetchSize}")
    private int jdbcOperationsFetchSize;
    @Value("${miracom.jdbcOperations.maxRows}")
    private int jdbcOperationsMaxRows;

    @Bean
    public JdbcOperations jdbcOperations() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource);
        bean.setFetchSize(jdbcOperationsFetchSize);
        bean.setMaxRows(jdbcOperationsMaxRows);
        return bean;
    }

    @Value("${miracom.namedParameterJdbcOperations.cacheLimit}")
    private int cacheLimit;

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations() {
        NamedParameterJdbcTemplate bean = new NamedParameterJdbcTemplate(jdbcOperations());
        bean.setCacheLimit(cacheLimit);
        return bean;
    }

    @Value("${miracom.transactionManager.defaultTimeout}")
    private int defaultTimeout;

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager bean = new DataSourceTransactionManager();
        bean.setDataSource(dataSource);
        bean.setDefaultTimeout(defaultTimeout);
        return bean;
    }

    @Bean
    public TransactionInterceptor defaultTransactionInterceptor() {
        TransactionInterceptor bean = new TransactionInterceptor();
        bean.setTransactionManager(transactionManager());
        {
            Properties props = new Properties();
//            props.setProperty("get*", "PROPAGATION_REQUIRED, readOnly, -Exception, -Throwable");
            props.setProperty("*", "PROPAGATION_REQUIRED, -Exception, -Throwable");
            bean.setTransactionAttributes(props);
        }
        return bean;
    }

    @Bean
    public TransactionInterceptor annotationTransactionInterceptor() {
        TransactionInterceptor bean = new TransactionInterceptor();
        bean.setTransactionManager(transactionManager());
        bean.setTransactionAttributeSource(new AnnotationTransactionAttributeSource());
        return bean;
    }

}
