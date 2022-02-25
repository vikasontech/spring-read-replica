package com.example.springreadreplica.config;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
  private Map<String, DataSource> map;

  @Autowired
  @Qualifier("masterDataSource")
  private DataSource masterDataSource;
  @Autowired
  @Qualifier("readDataSource")
  private DataSource readDataSource;

  @PostConstruct
  public void load() {
    map = new HashMap<>();
    map.put("master", masterDataSource);
    map.put("read", readDataSource);
  }

  @Override
  protected DataSource selectAnyDataSource() {
    return map.get(Constants.DEFAULT_TENANT_ID);
  }

  @Override
  protected DataSource selectDataSource(String tenantIdentifier) {
    return map.get(tenantIdentifier);
  }
}
