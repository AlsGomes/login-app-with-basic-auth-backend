package br.com.agdev.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agdev.domain.exceptions.ObjectNotFoundException;
import br.com.agdev.domain.model.User;
import br.com.agdev.domain.repositories.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var user = userRepository.findByEmail(email).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Não existe usuário com o e-mail %s", email)));;
		
		return new AppUser(user, getAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities;
		authorities = new HashSet<>();
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_BASIC");
		authorities.add(authority);
		
//		authorities = user.getPermissions().stream()
//				.map(p -> p.getDescription().toUpperCase())
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toSet());
		return authorities;
	}
}
