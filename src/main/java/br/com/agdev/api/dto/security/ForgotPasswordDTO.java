package br.com.agdev.api.dto.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ForgotPasswordDTO {

	@NotBlank
	@Email
	private String email;
}
