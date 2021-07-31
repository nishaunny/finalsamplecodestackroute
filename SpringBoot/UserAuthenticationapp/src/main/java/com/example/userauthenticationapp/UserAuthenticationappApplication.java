package com.example.userauthenticationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.example.userauthenticationapp")
public class UserAuthenticationappApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationappApplication.class, args);
	}

}
