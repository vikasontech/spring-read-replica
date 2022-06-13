package com.example.springreadreplica.config;

import com.example.springreadreplica.SpringReadReplicaApplication;
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
    @Qualifier("masterDataSource")
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
        Map<String, Object> hibernateProperties = new LinkedHashMap<>(jpaProperties.getProperties());

        hibernateProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        hibernateProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifier);
        hibernateProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, customMultiTenaceProvider);

        return builder.dataSource(dataSource)
                .packages(SpringReadReplicaApplication.class)
                .properties(hibernateProperties)
                .build();
    }
}
