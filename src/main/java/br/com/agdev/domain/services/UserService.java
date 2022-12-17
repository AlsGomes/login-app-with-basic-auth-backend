package br.com.agdev.domain.services;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agdev.core.security.AppUser;
import br.com.agdev.domain.exceptions.ObjectNotFoundException;
import br.com.agdev.domain.exceptions.UnathorizedDataAccessException;
import br.com.agdev.domain.model.User;
import br.com.agdev.domain.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public User findById(Long id) {
		if (!itIsMeById(id))
			throw new UnathorizedDataAccessException("Acesso não autorizado");

		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Não existe usuário com o ID %s", id)));
	}

	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		if (!itIsMeByEmail(email))
			throw new UnathorizedDataAccessException("Acesso não autorizado");

		return repository.findByEmail(email).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Não existe usuário com o e-mail %s", email)));
	}

	@Transactional
	public User save(User user) {
		return repository.save(user);
	}

	public static AppUser currentUser() {
		try {
			return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	private boolean itIsMeById(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Não existe usuário com o ID %s", id)));

		return currentUser().getUsername().equals(user.getEmail());
	}

	@Transactional(readOnly = true)
	private boolean itIsMeByEmail(String email) {
		User user = repository.findByEmail(email).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Não existe usuário com o e-mail %s", email)));

		return currentUser().getUsername().equals(user.getEmail());
	}
}
