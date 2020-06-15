package com.sunil.skillstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SkillsTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillsTrackerApplication.class, args);
	}

}
