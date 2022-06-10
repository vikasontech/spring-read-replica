package com.example.springreadreplica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.function.Function;

@Configuration
public class DataSourceConfig {

    @Autowired
    private ApplicationConfig applicationConfig;
    private final Function<ApplicationConfig.DataSourceConfig, DataSource> datasourceProvider = config -> DataSourceBuilder.create()
            .driverClassName(config.getDriverClassName())
            .url(config.getUrl())
            .password(config.getPassword())
            .username(config.getUsername())
            .build();

    @Bean(name = "writeDataSource")
    @Primary
    public DataSource getWriteDataSource() {
        return datasourceProvider.apply(applicationConfig.getWriteDatabase());
    }
    @Bean(name = "readDataSource")
    public DataSource getReadDataSource() {
        return datasourceProvider.apply(applicationConfig.getReadDatabase());
    }

}
