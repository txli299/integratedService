package com.gomobile.integratedService;

import ch.qos.logback.core.CoreConstants;
import com.gomobile.integratedService.model.*;
import com.gomobile.integratedService.repo.CafeRepository;
import com.gomobile.integratedService.repo.MachineRepository;
import com.gomobile.integratedService.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IntegratedServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(IntegratedServiceApplication.class, args);
	}
//	@Bean
//	CommandLineRunner addUserDemo(UserRepository userRepository){
//		return args -> {
//			User user = new User("abc1234","test2@gmail.com",200);
//			userRepository.insert(user);
//		};
//	}
//
//	@Bean
//	CommandLineRunner addCafeDemo(CafeRepository cafeRepository, MachineRepository machineRepository){
//		return args -> {
//			Machine machine1 = new Machine("pc1", MachineType.PC,false);
//			Machine machine2 = new Machine("pc2", MachineType.PC,false);
//			Machine machine3 = new Machine("pc3", MachineType.PC,false);
//			machineRepository.insert(machine1);
//			machineRepository.insert(machine2);
//			machineRepository.insert(machine3);
//			List<Machine> machineList = new ArrayList<Machine>();
//			machineList.add(machine1);
//			machineList.add(machine2);
//			machineList.add(machine3);
//			Coordinate coordinate = new Coordinate(123.21,132.12);
//			Cafe cafe = new Cafe("test cafe",coordinate,machineList);
//			cafeRepository.insert(cafe);
//		};
//	}
}
