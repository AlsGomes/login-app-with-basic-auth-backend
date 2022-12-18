package br.com.agdev.core.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils {

	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		String passwordToEncode = "12345678";
		encodeWithDefaultEncoder(passwordToEncode);
	}

	private static void encodeWithDefaultEncoder(String passwordToEncode) {
		System.out.println(PASSWORD_ENCODER.encode(passwordToEncode));
	}
}
