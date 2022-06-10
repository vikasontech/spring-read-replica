package com.example.springreadreplica.config;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
public class EntityManagerProvider {

    @Autowired
    @Qualifier("writeDataSource")
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private CustomMultiTenaceProvider customMultiTenaceProvider;

    @Autowired
    private TenantIdentifier tenantIdentifier;


    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder
    ) {
//        final Map<String, String> properties = jpaProperties.getProperties();
        Map<String, Object> hibernateProperties = new LinkedHashMap<>(jpaProperties.getProperties());

        hibernateProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        hibernateProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifier);
        hibernateProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, customMultiTenaceProvider);
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("spring.datasource.tomcat.testOnBorrow", true);
        hibernateProperties.put("spring.datasource.tomcat.validationQuery", "select 1");

        return builder.dataSource(dataSource)
                .packages( "com.example.springreadreplica")
                .properties(hibernateProperties)
                .build();
    }
}
