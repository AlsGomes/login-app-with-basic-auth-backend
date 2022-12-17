package br.com.agdev.core.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils {

	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		String passwordToEncode = "123";
		encodeWithDefaultEncoder(passwordToEncode);
	}

	private static void encodeWithDefaultEncoder(String passwordToEncode) {
		System.out.println(passwordEncoder.encode(passwordToEncode));
	}
}
