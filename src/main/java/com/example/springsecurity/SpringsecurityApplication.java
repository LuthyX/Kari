package com.example.springsecurity;

import com.example.springsecurity.models.AppUser;
import com.example.springsecurity.models.Role;
import com.example.springsecurity.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AppUserService appUserService) {
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null, "ROLE_MANAGER"));
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

//			appUserService.saveUser(new AppUser(null, "John Trav", "John", "1234", new ArrayList<>()));
//			appUserService.saveUser(new AppUser(null, "Shawn Man", "Will", "1234", new ArrayList<>()));
//			appUserService.saveUser(new AppUser(null, "Barney Stinson", "Pale", "1234", new ArrayList<>()));
			appUserService.saveUser(new AppUser("Joey Tribbiani", "Dave", "1234"));

//			appUserService.addRoleToUser("John", "ROLE_USER");
//			appUserService.addRoleToUser("Will", "ROLE_MANAGER");
//			appUserService.addRoleToUser("Pale", "ROLE_ADMIN");
			appUserService.addRoleToUser("Dave", "ROLE_SUPER_ADMIN");
//			appUserService.addRoleToUser("Will", "ROLE_USER");
//			appUserService.addRoleToUser("Dave", "ROLE_USER");
			appUserService.addRoleToUser("Dave", "ROLE_ADMIN");
		};

	}
}
