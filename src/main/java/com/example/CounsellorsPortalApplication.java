package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(exclude = {SecurityAutoConfiguration.class})
public class CounsellorsPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounsellorsPortalApplication.class, args);
	}

}
