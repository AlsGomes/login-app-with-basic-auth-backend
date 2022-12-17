package br.com.agdev.api.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.agdev.api.dto.security.ChangePasswordDTO;
import br.com.agdev.api.dto.user.UserDTO;
import br.com.agdev.api.dto.user.UserDTOInput;
import br.com.agdev.api.dto.user.UserDTOUpdate;
import br.com.agdev.api.mapper.user.UserInputMapper;
import br.com.agdev.api.mapper.user.UserMapper;
import br.com.agdev.api.mapper.user.UserUpdateMapper;
import br.com.agdev.domain.model.User;
import br.com.agdev.domain.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResource {

	private UserService service;
	private UserMapper mapper;
	private UserInputMapper inputMapper;
	private UserUpdateMapper updateMapper;

	public UserResource(UserService service) {
		this.service = service;
		this.mapper = new UserMapper();
		this.inputMapper = new UserInputMapper();
		this.updateMapper = new UserUpdateMapper();
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> usersDTO = mapper.toDTOList(users);
		return ResponseEntity.ok().body(usersDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		User user = service.findById(id);
		UserDTO userDTO = mapper.toDTO(user);
		return ResponseEntity.ok().body(userDTO);
	}

	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserDTOInput input) {
		User entity = inputMapper.toEntity(input);
		entity = service.save(entity);
		UserDTO output = mapper.toDTO(entity);
		
		URI uri = createdURI(output.getId().toString());
		return ResponseEntity.created(uri).body(output);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody @Valid UserDTOUpdate updateDTO, @PathVariable Long id) {
		User entity = service.findById(id);
		updateMapper.copyPropertiesToEntity(entity, updateDTO);
		entity = service.update(entity);

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/change-my-password")
	public ResponseEntity<Void> changeMyPassword(@RequestBody @Valid ChangePasswordDTO changePassDTO) {
		service.changeMyOwnPassword(changePassDTO);
		return ResponseEntity.noContent().build();
	}

	private URI createdURI(String id) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
		return uri;
	}
}
