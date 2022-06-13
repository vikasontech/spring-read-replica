package com.example.springreadreplica.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    private DataSourceConfig replicaDatabase;
    private DataSourceConfig masterDatabase;

    DataSourceConfig getReplicaDatabase() {
        return replicaDatabase;
    }

    void setReplicaDatabase(final DataSourceConfig replicaDatabase) {
        this.replicaDatabase = replicaDatabase;
    }

    DataSourceConfig getMasterDatabase() {
        return masterDatabase;
    }

    void setMasterDatabase(final DataSourceConfig masterDatabase) {
        this.masterDatabase = masterDatabase;
    }

    static class DataSourceConfig {
        private String url;
        private String username;
        private String password;
        private String driverClassName;

        String getUrl() {
            return url;
        }

        void setUrl(final String url) {
            this.url = url;
        }

        String getUsername() {
            return username;
        }

        void setUsername(final String username) {
            this.username = username;
        }

        String getPassword() {
            return password;
        }

        void setPassword(final String password) {
            this.password = password;
        }

        String getDriverClassName() {
            return driverClassName;
        }

        void setDriverClassName(final String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }
}
