package com.gomobile.integratedService;

import ch.qos.logback.core.CoreConstants;
import com.gomobile.integratedService.model.*;
import com.gomobile.integratedService.repo.CafeRepository;
import com.gomobile.integratedService.repo.MachineRepository;
import com.gomobile.integratedService.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IntegratedServiceApplication  {
	public static void main(String[] args) {
		SpringApplication.run(IntegratedServiceApplication.class, args);
	}
//	@Bean
//	CommandLineRunner addUserDemo(UserRepository userRepository){
//		return args -> {
//			User user = new User("abc12345","test3@gmail.com",200);
//			userRepository.insert(user);
//		};
//	}
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(10); // Adjust this value as necessary
		scheduler.setThreadNamePrefix("scheduled-task-");
		scheduler.initialize();
		return scheduler;
	}



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
//			Cafe cafe1 = new Cafe("cafe 1",coordinate,machineList);
//			cafeRepository.insert(cafe1);
//
//			Machine machine4 = new Machine("ps1", MachineType.PS,false);
//			Machine machine5 = new Machine("ps2", MachineType.PS,false);
//			Machine machine6 = new Machine("ps3", MachineType.PS,false);
//			machineRepository.insert(machine4);
//			machineRepository.insert(machine5);
//			machineRepository.insert(machine6);
//			List<Machine> machineList2 = new ArrayList<Machine>();
//			machineList2.add(machine4);
//			machineList2.add(machine5);
//			machineList2.add(machine6);
//			Coordinate coordinate2 = new Coordinate(100.00,50.00);
//			Cafe cafe2 = new Cafe("cafe 2",coordinate2,machineList2);
//			cafeRepository.insert(cafe2);
//
//			Machine machine7 = new Machine("ps4", MachineType.PS,false);
//			Machine machine8 = new Machine("pc4", MachineType.PC,false);
//			Machine machine9 = new Machine("ps5", MachineType.PS,false);
//			machineRepository.insert(machine7);
//			machineRepository.insert(machine8);
//			machineRepository.insert(machine9);
//			List<Machine> machineList3 = new ArrayList<Machine>();
//			machineList3.add(machine7);
//			machineList3.add(machine8);
//			machineList3.add(machine9);
//			Coordinate coordinate3 = new Coordinate(200.00,150.00);
//			Cafe cafe3 = new Cafe("cafe 3",coordinate3,machineList3);
//			cafeRepository.insert(cafe3);
//		};
//	}
}
