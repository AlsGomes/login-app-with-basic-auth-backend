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
import br.com.agdev.api.dto.security.TokenResponseDTO;
import br.com.agdev.api.dto.user.UserDTOPermission;
import br.com.agdev.api.mapper.user.UserPermissionMapper;
import br.com.agdev.domain.model.User;
import br.com.agdev.domain.services.AuthenticationService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationResource {

	private AuthenticationService authenticationService;
	private UserPermissionMapper userPermissionMapper;

	public AuthenticationResource(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
		this.userPermissionMapper = new UserPermissionMapper();
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTOPermission> login(@RequestBody @Valid LoginDTO loginDto) {
		User user = authenticationService.authenticate(loginDto);
		UserDTOPermission userPermissionDto = userPermissionMapper.toDTO(user);
		return ResponseEntity.ok(userPermissionDto);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<TokenResponseDTO> forgotPassword(@RequestBody @Valid ForgotPasswordDTO dto) {
		TokenResponseDTO token = new TokenResponseDTO();
		token.setToken(authenticationService.beginRecoveryPasswordFlow(dto));
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/create-new-password")
	public ResponseEntity<Void> createNewPassword(@RequestBody @Valid NewPasswordDTO dto) {
		authenticationService.createNewPassword(dto);
		return ResponseEntity.ok().build();
	}	
}
