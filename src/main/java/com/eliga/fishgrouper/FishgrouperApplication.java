package com.eliga.fishgrouper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
@EnableConfigurationProperties
@EntityScan("com")
public class FishgrouperApplication {
	public static void main(String[] args) {
		SpringApplication.run(FishgrouperApplication.class, args);
	}

}
