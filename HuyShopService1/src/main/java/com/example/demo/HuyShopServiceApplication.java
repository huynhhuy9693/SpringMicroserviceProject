package com.example.demo;

import com.cloudinary.Cloudinary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
public class HuyShopServiceApplication {

	@Bean
	public Cloudinary cloudinaryConfig() {
		Cloudinary cloudinary = null;
		Map config = new HashMap();
		config.put("cloud_name", "huyhuynh");
		config.put("api_key", "186243663589179");
		config.put("api_secret", "xIhyZNk6NYFIuMSHRhZP0srJL_k");
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}
	public static void main(String[] args) {
		SpringApplication.run(HuyShopServiceApplication.class, args);
	}

}
