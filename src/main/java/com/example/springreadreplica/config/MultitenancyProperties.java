package com.example.springreadreplica.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.multitenancy")
public class MultitenancyProperties {
  @NestedConfigurationProperty
  private DataSourceProperties datasource1;

  @NestedConfigurationProperty
  private DataSourceProperties datasource2;


  DataSourceProperties getDatasource1() {
    return datasource1;
  }

  void setDatasource1(DataSourceProperties datasource1) {
    this.datasource1 = datasource1;
  }

  DataSourceProperties getDatasource2() {
    return datasource2;
  }

  void setDatasource2(DataSourceProperties datasource2) {
    this.datasource2 = datasource2;
  }

}
