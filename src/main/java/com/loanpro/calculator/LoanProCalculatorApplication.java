package com.loanpro.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoanProCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanProCalculatorApplication.class, args);
	}

}
