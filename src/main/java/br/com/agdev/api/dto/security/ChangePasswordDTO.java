package br.com.agdev.api.dto.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.agdev.api.dto.validation.EqualityBetween;
import br.com.agdev.api.dto.validation.Password;
import lombok.Data;

@Data
@EqualityBetween(fields = { "newPassword", "confirmationNewPassword" })
public class ChangePasswordDTO {

	@NotBlank
	private String oldPassword;

	@Password(restrictions = { ":", "@" })
	@Size(min = 8, max = 70)
	private String newPassword;

	@Password(restrictions = { ":", "@" })
	@Size(min = 8, max = 70)
	private String confirmationNewPassword;
}
