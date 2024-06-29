package com.grocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.grocery.*"})
public class GroceryBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryBookingApplication.class, args);
	}

}
