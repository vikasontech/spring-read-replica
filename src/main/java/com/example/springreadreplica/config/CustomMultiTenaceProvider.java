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
public class CustomMultiTenaceProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDatasource;
    @Autowired
    @Qualifier("replicaDataSource")
    private DataSource replicaDataSource;
    final Map<String, DataSource> dataSourceMap= new HashMap<>(2);

    @PostConstruct
    public void setup(){
        dataSourceMap.put(AppConstants.MASTER, masterDatasource);
        dataSourceMap.put(AppConstants.REPLICA, replicaDataSource);
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return masterDatasource;
    }

    @Override
    protected DataSource selectDataSource(final String tenantIdentifier) {
        return dataSourceMap.get(tenantIdentifier);
    }
}
