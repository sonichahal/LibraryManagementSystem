package com.dz.student.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class DBConfig {

    private static final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HBM2DLL = "hibernate.hbm2ddl.auto";

    @Value("${mysql.driver}")
    private String dbDriver;

    @Value("${mysql.url}")
    private String dbUrl;

    @Value("${mysql.username}")
    private String dbUserName;

    @Value("${mysql.password}")
    private String dbPassword;

    @Value("${mysql.hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${mysql.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${mysql.hibernate.hbm2ddl.auto}")
    private String hibernatehbm2ddlAuto;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(dbUrl);
        ds.setUsername(dbUserName);
        ds.setPassword(dbPassword);
        return ds;
    }

    private Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.setProperty(PROPERTY_DIALECT, hibernateDialect);
        prop.setProperty(PROPERTY_SHOW_SQL, hibernateShowSql);
        prop.setProperty(PROPERTY_HBM2DLL, hibernatehbm2ddlAuto);
        return prop;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        Package packageName = getClass().getPackage();
        sessionFactory.setPackagesToScan("com.dz.student.model");
        return sessionFactory;
    }

    @Bean
    HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
}
