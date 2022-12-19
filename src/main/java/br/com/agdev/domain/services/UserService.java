package br.com.agdev.domain.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.agdev.api.dto.security.ChangePasswordDTO;
import br.com.agdev.core.security.exceptions.AuthenticationException;
import br.com.agdev.domain.exceptions.ObjectNotFoundException;
import br.com.agdev.domain.exceptions.UnathorizedDataAccessException;
import br.com.agdev.domain.model.Permission;
import br.com.agdev.domain.model.User;
import br.com.agdev.domain.repositories.PermissionRepository;
import br.com.agdev.domain.repositories.UserRepository;

@Service
public class UserService {

	private static final long ID_PERMISSION_BASIC = 2L;
	
	private UserRepository repository;
	private PermissionRepository permissionRepository;
	private PasswordEncoder passwordEncoder;

	public UserService(
			UserRepository repository,
			PermissionRepository permissionRepository,
			PasswordEncoder passwordEncoder) {

		this.repository = repository;
		this.permissionRepository = permissionRepository;
		this.passwordEncoder = passwordEncoder;
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
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		addInitialPermissionFor(user);		
		return repository.save(user);
	}

	@Transactional
	public User update(User user) {
		if (!itIsMeById(user.getId()))
			throw new UnathorizedDataAccessException("Acesso não autorizado");
		
		return repository.save(user);
	}

	@Transactional
	public void changeForgotPassword(String email, String rawPassword) {
		User user = repository.findByEmail(email).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Não existe usuário com o e-mail %s", email)));
		
		user.setPassword(passwordEncoder.encode(rawPassword));
		repository.save(user);
	}

	public void changeMyOwnPassword(ChangePasswordDTO changePassDTO) {
		User user = AuthenticationService.currentUser().getUser();
		boolean isValidOldPassword = passwordEncoder.matches(changePassDTO.getOldPassword(), user.getPassword());
		
		if (!isValidOldPassword)
			throw new AuthenticationException("Não foi possível alterar a senha. A senha atual informada está incorreta");

		user.setPassword(passwordEncoder.encode(changePassDTO.getNewPassword()));
		repository.save(user);		
	}

	@Transactional(readOnly = true)
	private boolean itIsMeById(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Não existe usuário com o ID %s", id)));

		return AuthenticationService.currentUser().getUsername().equals(user.getEmail());
	}

	@Transactional(readOnly = true)
	private boolean itIsMeByEmail(String email) {
		User user = repository.findByEmail(email).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Não existe usuário com o e-mail %s", email)));

		return AuthenticationService.currentUser().getUsername().equals(user.getEmail());
	}
	
	private void addInitialPermissionFor(User user) {
		Permission basicPermission = permissionRepository.findById(ID_PERMISSION_BASIC).orElseThrow(
				() -> new ObjectNotFoundException(String.format("Permissão com ID %s não existe", ID_PERMISSION_BASIC)));
		
		user.getPermissions().add(basicPermission);
	}
}
