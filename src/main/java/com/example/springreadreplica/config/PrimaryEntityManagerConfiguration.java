package com.example.springreadreplica.config;

import com.example.springreadreplica.others.Constants;
import com.example.springreadreplica.others.ReadOnlyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

//@Configuration
//@EnableJpaRepositories(
//		basePackages = "com.example",
//    excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
//    entityManagerFactoryRef = "entityManagerFactory"
//)
public class PrimaryEntityManagerConfiguration {
  @org.springframework.beans.factory.annotation.Value("${spring.datasource.username}")
  private String username;

  @org.springframework.beans.factory.annotation.Value("${spring.datasource.password}")
  private String password;

  @Bean
  @Primary
  public DataSource dataSource() throws Exception {
    return DataSourceBuilder.create()
        .url(Constants.url)
        .username(username)
        .password(password)
        .driverClassName("com.mysql.jdbc.Driver")
        .build();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("dataSource") DataSource dataSource) {
    return builder.dataSource(dataSource)
        .packages("com.example.springreadreplica")
        .persistenceUnit("main")
        .build();
  }
}
