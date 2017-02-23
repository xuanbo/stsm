package com.whut.stsm.provider.configuration.tx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 事务管理
 *
 * Created by null on 2017/2/22.
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration implements TransactionManagementConfigurer {

    @Resource(name = "txManager")
    private PlatformTransactionManager txManager;

    @Bean
    public PlatformTransactionManager jpaTxManager(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
     * 默认使用DataSourceTransactionManager
     *
     * @return DataSourceTransactionManager
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager;
    }
}
