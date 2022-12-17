package br.com.agdev.api.dto.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.agdev.api.dto.validation.EqualityBetween;
import br.com.agdev.api.dto.validation.Password;
import lombok.Data;

@Data
@EqualityBetween(fields = { "password", "confirmationPassword" })
public class NewPasswordDTO {

	@NotBlank
	private String token;

	@Password(restrictions = { ":", "@" })
	@Size(min = 8, max = 70)
	private String password;

	@Password(restrictions = { ":", "@" })
	@Size(min = 8, max = 70)
	private String confirmationPassword;
}
