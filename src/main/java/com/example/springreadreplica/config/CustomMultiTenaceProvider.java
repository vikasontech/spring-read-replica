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
    @Qualifier("writeDataSource")
    private DataSource writeDataSource;
    @Autowired
    @Qualifier("readDataSource")
    private DataSource readDataSource;
    final Map<String, DataSource> dataSourceMap= new HashMap<>(2);

    @PostConstruct
    public void setup(){
        dataSourceMap.put("write", writeDataSource);
        dataSourceMap.put("read", readDataSource);
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return writeDataSource;
    }

    @Override
    protected DataSource selectDataSource(final String tenantIdentifier) {
        return dataSourceMap.get(tenantIdentifier);
    }
}
