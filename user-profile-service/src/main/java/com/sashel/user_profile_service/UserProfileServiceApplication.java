package com.sashel.user_profile_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sashel.user_profile_service.*")
public class UserProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfileServiceApplication.class, args);
	}

}
