package com.xyz.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GrabbyCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrabbyCoreApplication.class, args);
	}
}
