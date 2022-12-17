package br.com.agdev.api.resources;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agdev.api.dto.LoginDTO;

@RestController
@RequestMapping("/api/login")
public class LoginResource {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	public LoginResource(
			UserDetailsService userDetailsService, 
			PasswordEncoder passwordEncoder) {
		
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping
	public ResponseEntity<Void> login(@RequestBody @Valid LoginDTO dto) {
		var user = userDetailsService.loadUserByUsername(dto.getEmail());
		var isAuthenticated = passwordEncoder.matches(dto.getPassword(), user.getPassword());
		
		if (isAuthenticated) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
