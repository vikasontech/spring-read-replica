package com.example.springreadreplica.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    private DataSourceConfig readDatabase;
    private DataSourceConfig writeDatabase;

    DataSourceConfig getReadDatabase() {
        return readDatabase;
    }

    void setReadDatabase(final DataSourceConfig readDatabase) {
        this.readDatabase = readDatabase;
    }

    DataSourceConfig getWriteDatabase() {
        return writeDatabase;
    }

    void setWriteDatabase(final DataSourceConfig writeDatabase) {
        this.writeDatabase = writeDatabase;
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
