package com.ikeda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ikeda.repository")
@EntityScan(basePackages = "com.ikeda.entity")
public class RentalSystemProjectApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(RentalSystemProjectApplication.class, args);
    }
}
