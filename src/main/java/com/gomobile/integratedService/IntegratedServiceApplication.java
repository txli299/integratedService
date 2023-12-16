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
import java.util.UUID;

@SpringBootApplication
public class IntegratedServiceApplication  {
	public static void main(String[] args) {
		SpringApplication.run(IntegratedServiceApplication.class, args);
	}

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
//			String cafe1Id = UUID.randomUUID().toString();
//			Machine machine1 = new Machine("pc1", MachineType.PC,cafe1Id,false);
//			Machine machine2 = new Machine("pc2", MachineType.PC,cafe1Id,false);
//			Machine machine3 = new Machine("pc3", MachineType.PC,cafe1Id,false);
//			machineRepository.insert(machine1);
//			machineRepository.insert(machine2);
//			machineRepository.insert(machine3);
//
//			List<Machine> machineList = new ArrayList<Machine>();
//			machineList.add(machine1);
//			machineList.add(machine2);
//			machineList.add(machine3);
//			Coordinate coordinate = new Coordinate(42.36,-71.05);
//			Cafe cafe1 = new Cafe("Boston Cafe",coordinate,machineList,"123 Street, Boston");
//			cafe1.setId(cafe1Id);
//			cafeRepository.insert(cafe1);
//
//			String cafe2Id = UUID.randomUUID().toString();
//			Machine machine4 = new Machine("ps1", MachineType.PS,cafe2Id,false);
//			Machine machine5 = new Machine("ps2", MachineType.PS,cafe2Id,false);
//			Machine machine6 = new Machine("ps3", MachineType.PS,cafe2Id,false);
//			machineRepository.insert(machine4);
//			machineRepository.insert(machine5);
//			machineRepository.insert(machine6);
//
//			List<Machine> machineList2 = new ArrayList<Machine>();
//			machineList2.add(machine4);
//			machineList2.add(machine5);
//			machineList2.add(machine6);
//			Coordinate coordinate2 = new Coordinate(28.53,-81.37);
//			Cafe cafe2 = new Cafe("Orlando Cafe",coordinate2,machineList2,"456 Street, Orlando");
//			cafe2.setId(cafe2Id);
//			cafeRepository.insert(cafe2);
//
//			String cafe3Id = UUID.randomUUID().toString();
//			Machine machine7 = new Machine("ps4", MachineType.PS,cafe3Id,false);
//			Machine machine8 = new Machine("pc4", MachineType.PC,cafe3Id,false);
//			Machine machine9 = new Machine("ps5", MachineType.PS,cafe3Id,false);
//			machineRepository.insert(machine7);
//			machineRepository.insert(machine8);
//			machineRepository.insert(machine9);
//
//			List<Machine> machineList3 = new ArrayList<Machine>();
//			machineList3.add(machine7);
//			machineList3.add(machine8);
//			machineList3.add(machine9);
//			Coordinate coordinate3 = new Coordinate(37.77,-122.41);
//			Cafe cafe3 = new Cafe("San Francisco Cafe",coordinate3,machineList3,"789 Street, San Francisco");
//			cafe3.setId(cafe3Id);
//
//			cafeRepository.insert(cafe3);
//		};
//	}
}
