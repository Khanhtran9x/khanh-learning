package com.cmckayling.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGatewayResilience4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayResilience4jApplication.class, args);
	}

}
