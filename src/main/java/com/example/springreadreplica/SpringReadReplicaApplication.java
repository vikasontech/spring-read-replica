package com.example.springreadreplica;

import com.example.springreadreplica.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(value = ApplicationConfig.class)
public class SpringReadReplicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReadReplicaApplication.class, args);
	}

}