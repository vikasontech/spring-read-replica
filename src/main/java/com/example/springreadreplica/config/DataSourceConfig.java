package com.example.springreadreplica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

//  @Autowired
//  private MultitenancyProperties multitenancyProperties;

  @Bean(name = { "dataSource", "masterDataSource" })
  public DataSource dataSource1(MultitenancyProperties multitenancyProperties) {
    final DataSourceProperties datasource1 = multitenancyProperties.getDatasource1();
    DataSourceBuilder factory = DataSourceBuilder
//        .create(datasource1.getClassLoader())
        .create()
        .driverClassName("com.mysql.jdbc.Driver")
        .username("merchant_profile")
        .password("Welcome1")
        .url("jdbc:mysql://localhost:3306/mapi?useSSL=false&characterEncoding=UTF-8");
    return factory.build();
  }
  @Bean(name = { "dataSource", "readDataSource" })
//  @ConfigurationProperties(prefix = "spring.multitenancy.datasource2")
  public DataSource dataSource2(MultitenancyProperties multitenancyProperties) {
    final DataSourceProperties datasource2 = multitenancyProperties.getDatasource2();
    DataSourceBuilder factory = DataSourceBuilder
//        .create(datasource1.getClassLoader())
        .create()
        .driverClassName("com.mysql.jdbc.Driver")
        .username("merchant_profile")
        .password("Welcome1")
        .url("jdbc:mysql://localhost:3306/mapi?useSSL=false&characterEncoding=UTF-8");

    return factory.build();
  }
}
