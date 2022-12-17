package br.com.agdev.api.mapper.user;

import br.com.agdev.api.dto.user.UserDTO;
import br.com.agdev.api.mapper.DefaultMapper;
import br.com.agdev.domain.model.User;

public class UserMapper extends DefaultMapper<User, UserDTO> {

	public UserMapper() {
		super(User.class, UserDTO.class);
	}

}
