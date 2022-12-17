package br.com.agdev.api.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.agdev.api.dto.validation.EqualityBetween;
import br.com.agdev.api.dto.validation.Password;
import lombok.Data;

@Data
@EqualityBetween(fields = { "password", "confirmationPassword" })
public class UserDTOInput {

	@NotBlank
	@Size(min = 3, max = 70)
	private String name;

	@NotBlank
	@Email
	private String email;

	@Password(restrictions = { ":", "@" })
	@Size(min = 8, max = 70)
	private String password;

	@Password(restrictions = { ":", "@" })
	@Size(min = 8, max = 70)
	private String confirmationPassword;
}
