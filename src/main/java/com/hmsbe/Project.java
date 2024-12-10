package com.hmsbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EntityScan("com.hmsbe.entity")
@EnableJpaRepositories("com.hmsbe.repository")
public class Project extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Project.class, args);
	}

}
