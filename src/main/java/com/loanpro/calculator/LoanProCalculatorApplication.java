package com.loanpro.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackages= "com.loanpro.calculator.models")
@EnableFeignClients
public class LoanProCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanProCalculatorApplication.class, args);
	}

}
