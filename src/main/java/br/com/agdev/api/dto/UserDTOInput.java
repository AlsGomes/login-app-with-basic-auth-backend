package br.com.agdev.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDTOInput {

	@NotBlank
	@Size(min = 3, max = 70)
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 8, max = 70)
	private String password;

	@NotBlank
	@Size(min = 8, max = 70)
	private String confirmationPassword;
}
