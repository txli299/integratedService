package com.gomobile.integratedService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntegratedServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(IntegratedServiceApplication.class, args);
	}
//	@Bean
//	CommandLineRunner runner(UserRepository repository){
//		return args -> {
//			User user = new User("abc1234","test2@gmail.com",200);
//			//repository.insert(user);
//		};
//	}
}
