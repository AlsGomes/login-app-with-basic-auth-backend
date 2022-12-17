package br.com.agdev.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String password;
}
