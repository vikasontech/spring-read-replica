package com.example.springreadreplica.config;

import com.example.springreadreplica.SpringReadReplicaApplication;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.example.springreadreplica.config.AppConstants.REPLICA;
import static com.example.springreadreplica.config.AppConstants.TENANT_IDENTIFIER;

@Configuration
public class MultiTenantConfig {
}

class MultiTenanceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final String method = request.getMethod();
        final String requestURI = request.getRequestURI();

        if (method.equals("GET")) {
            request.setAttribute(TENANT_IDENTIFIER, AppConstants.REPLICA);
        }

        return true;
    }
}

@Configuration
class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new MultiTenanceInterceptor());
    }
}

@Configuration
class DataSourceConfig {
    @Autowired
    private ApplicationConfig applicationConfig;
    private static Function<ApplicationConfig.DataSourceConfig, DataSource> createDataSource = dataSourceConfig -> DataSourceBuilder.create()
            .url(dataSourceConfig.getUrl())
            .password(dataSourceConfig.getPassword())
            .username(dataSourceConfig.getUsername())
            .driverClassName(dataSourceConfig.getDriverClassName()).build();

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        final ApplicationConfig.DataSourceConfig masterDatabase = applicationConfig.getMasterDatabase();
        return createDataSource.apply(masterDatabase);
    }

    @Bean(name = "replicaDataSource")
    public DataSource replicaDataSource() {
        final ApplicationConfig.DataSourceConfig replicaDatabase = applicationConfig.getReplicaDatabase();
        return createDataSource.apply(replicaDatabase);

    }
}

@Component
class TenantResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(i -> i.getAttribute(TENANT_IDENTIFIER, RequestAttributes.SCOPE_REQUEST))
                .map(String.class::cast)
                .orElse(AppConstants.MASTER);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}

@Component
class TenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource master;

    @Autowired
    @Qualifier("replicaDataSource")
    private DataSource replica;

    @Override
    protected DataSource selectAnyDataSource() {
        return master;
    }

    @Override
    protected DataSource selectDataSource(final String tenantIdentifier) {
        if (tenantIdentifier.equals(REPLICA)) {
            return replica;
        }
        return master;
    }
}

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
class EntityManagerProvider {
    @Autowired
    @Qualifier("masterDataSource")
    private DataSource master;

    @Autowired
    private JpaProperties jpaProperties ;

    @Autowired
    private TenantConnectionProvider tenantConnectionProvider;

    @Autowired
    private TenantResolver tenantResolver;


    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder
                                                           ) {
        Map<String, Object> hibernateProperties = new LinkedHashMap<>(jpaProperties.getProperties());

        hibernateProperties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        hibernateProperties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
        hibernateProperties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, tenantConnectionProvider);

        return builder.dataSource(master)
                .packages(SpringReadReplicaApplication.class)
                .properties(hibernateProperties)
                .build();
    }

}