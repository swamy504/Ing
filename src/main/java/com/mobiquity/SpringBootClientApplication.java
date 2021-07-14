package com.mobiquity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.mobiquity.intergration.service.IngIntergrationService;
import com.mobiquity.intergration.service.IngIntergrationServiceImpl;

@SpringBootApplication
public class SpringBootClientApplication {
	
	@Bean
	RestTemplate getTempalte() {
		return new RestTemplate();
	}
	
	@Bean
	IngIntergrationService instanceIngService() {
		return new IngIntergrationServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootClientApplication.class, args);
	}

}
