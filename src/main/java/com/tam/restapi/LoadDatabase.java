package com.tam.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(CustomerRepository customerRepo, UserRepository userRepo) {

		return args -> {
			log.info("Preloading " + customerRepo.save(new Customer("Bilbo", "Baggins")));
			log.info("Preloading " + customerRepo.save(new Customer("Frodo", "Baggins")));
			log.info("Preloading " + userRepo.save(new User("One", "User")));
			log.info("Preloading " + userRepo.save(new User("Two", "Admin")));
		};
	}
}