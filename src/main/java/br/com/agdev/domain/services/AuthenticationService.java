package br.com.agdev.domain.services;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agdev.api.dto.security.ForgotPasswordDTO;
import br.com.agdev.api.dto.security.LoginDTO;
import br.com.agdev.api.dto.security.NewPasswordDTO;
import br.com.agdev.core.security.AppUser;
import br.com.agdev.core.security.exceptions.AuthenticationException;
import br.com.agdev.domain.model.User;

@Service
public class AuthenticationService {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	private CommunicationService communicationService;
	
	private UserService userService;

	public AuthenticationService(
			UserDetailsService userDetailsService, 
			PasswordEncoder passwordEncoder,
			CommunicationService communicationService,
			UserService userService) {

		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.communicationService = communicationService;
		this.userService = userService;
	}

	public User authenticate(LoginDTO dto) {
		UserDetails user = userDetailsService.loadUserByUsername(dto.getEmail());
		boolean isAuthenticated = passwordEncoder.matches(dto.getPassword(), user.getPassword());

		if (!isAuthenticated)
			throw new AuthenticationException("Usuário ou senha inválidos");

		return ((AppUser) user).getUser();
	}

	public void beginRecoveryPasswordFlow(@Valid ForgotPasswordDTO dto) {
		User user = getUserByEmail(dto.getEmail());

		String passwordEncoded = passwordEncoder.encode(user.getPassword());
		String email = user.getEmail();
		String rawToken = email + ":" + passwordEncoded;
		String encodedToken = Base64.getEncoder().encodeToString(rawToken.getBytes());

		communicationService.communicate(user, "Criação de nova senha", encodedToken);
	}

	public void createNewPassword(NewPasswordDTO dto) {
		String decodedToken = new String(Base64.getDecoder().decode(dto.getToken()));
		String[] token = decodedToken.split(":");
		String email = token[0];
		String encodedPassword = token[1];
		User user = getUserByEmail(email);
		boolean isTokenValid = passwordEncoder.matches(user.getPassword(), encodedPassword);

		if (!isTokenValid)
			throw new AuthenticationException("Não foi possível criar a nova senha. Os tokens não coincidem");
		
		userService.changeForgotPassword(email, dto.getPassword());
	}

	public static AppUser currentUser() {
		try {
			return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	private User getUserByEmail(String email) {
		AppUser appUser = (AppUser) userDetailsService.loadUserByUsername(email);
		User user = appUser.getUser();
		return user;
	}
}
