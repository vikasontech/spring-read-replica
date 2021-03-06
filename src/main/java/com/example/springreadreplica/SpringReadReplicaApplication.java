package com.example.springreadreplica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringReadReplicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReadReplicaApplication.class, args);
	}

}