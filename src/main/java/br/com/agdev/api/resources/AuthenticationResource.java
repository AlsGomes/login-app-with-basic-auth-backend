package br.com.agdev.api.resources;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agdev.api.dto.security.ForgotPasswordDTO;
import br.com.agdev.api.dto.security.LoginDTO;
import br.com.agdev.api.dto.security.NewPasswordDTO;
import br.com.agdev.domain.services.AuthenticationService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationResource {

	private AuthenticationService authenticationService;

	public AuthenticationResource(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody @Valid LoginDTO dto) {
		authenticationService.authenticate(dto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<Void> forgotPassword(@RequestBody @Valid ForgotPasswordDTO dto) {
		authenticationService.beginRecoveryPasswordFlow(dto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/create-new-password")
	public ResponseEntity<Void> createNewPassword(@RequestBody @Valid NewPasswordDTO dto) {
		authenticationService.createNewPassword(dto);
		return ResponseEntity.ok().build();
	}	
}
