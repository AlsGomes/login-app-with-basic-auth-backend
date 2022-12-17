package br.com.agdev.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agdev.domain.exceptions.ObjectNotFoundException;
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
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Não existe usuário com o ID %s", id)));
	}

	@Transactional
	public User save(User user) {
		return repository.save(user);
	}
}
