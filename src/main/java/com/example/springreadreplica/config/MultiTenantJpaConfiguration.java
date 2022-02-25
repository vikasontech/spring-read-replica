package com.example.springreadreplica.config;

import com.example.springreadreplica.entity.AddressInfoEntity;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

//todo: resource: http://anakiou.blogspot.com/2015/08/multi-tenant-application-with-spring.html


@Configuration
@EnableConfigurationProperties(JpaProperties.class)
public class MultiTenantJpaConfiguration {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private JpaProperties jpaProperties;

  @Autowired
  private MultiTenantConnectionProvider multiTenantConnectionProvider;

  @Autowired
  private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
    Map<String, Object> hibernateProperties = new LinkedHashMap<>(jpaProperties.getHibernateProperties(dataSource));

    hibernateProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
    hibernateProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
    hibernateProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
    hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

    return builder.dataSource(dataSource)
        .packages(AddressInfoEntity.class.getPackage().getName())
        .properties(hibernateProperties)
        .build();
  }

}
