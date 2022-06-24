package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HuyShopService2Application {

	public static void main(String[] args) {
		SpringApplication.run(HuyShopService2Application.class, args);
	}

}
