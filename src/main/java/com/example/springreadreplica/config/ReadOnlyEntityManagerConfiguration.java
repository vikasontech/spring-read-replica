package com.example.springreadreplica.config;

import com.example.springreadreplica.others.Constants;
import com.example.springreadreplica.others.ReadOnlyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

//@Configuration
//@EnableJpaRepositories(
//		basePackages = "com.example",
//    includeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
//    entityManagerFactoryRef = "readEntityManagerFactory"
//)

public class ReadOnlyEntityManagerConfiguration {
  @org.springframework.beans.factory.annotation.Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Bean
  public DataSource readDataSource() throws Exception {
    return DataSourceBuilder.create()
        .url(Constants.readOnlyDbUrl)
        .username(username)
        .password(password)
        .driverClassName("com.mysql.jdbc.Driver")
        .build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("readDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource)
        .packages("com.example.springreadreplica")
        .persistenceUnit("read")
        .build();
  }
}
