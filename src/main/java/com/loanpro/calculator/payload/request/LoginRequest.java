package com.loanpro.calculator.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
