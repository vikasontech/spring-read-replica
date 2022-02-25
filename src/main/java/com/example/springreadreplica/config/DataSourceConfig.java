package com.example.springreadreplica.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Bean(name = "masterDataSource")
  @Primary
  public DataSource masterDataSource() {

    return DataSourceBuilder
        .create()
        .driverClassName("com.mysql.jdbc.Driver")
        .username("merchant_profile")
        .password("Welcome1")
        .url("jdbc:mysql://localhost:3306/mapi?useSSL=false&characterEncoding=UTF-8")
        .build();
  }

  @Bean(name = "readDataSource" )
  public DataSource readDataSource() {

    return DataSourceBuilder
        .create()
        .driverClassName("com.mysql.jdbc.Driver")
        .username("merchant_profile")
        .password("Welcome1")
        .url("jdbc:mysql://localhost:3306/mapi?useSSL=false&characterEncoding=UTF-8")
        .build();

  }
}
