package com.shoppingmakiia.CatalogServicesApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CatalogServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServicesApplication.class, args);
	}

}
