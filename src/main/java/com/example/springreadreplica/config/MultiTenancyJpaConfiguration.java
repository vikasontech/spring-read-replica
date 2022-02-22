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
public class MultiTenancyJpaConfiguration {

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
    Map<String, Object> hibernateProps = new LinkedHashMap<>(jpaProperties.getHibernateProperties(dataSource));

    hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
    hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
    hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
    hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

    return builder.dataSource(dataSource)
        .packages(AddressInfoEntity.class.getPackage().getName())
        .properties(hibernateProps)
        .jta(false).build();
  }

}
