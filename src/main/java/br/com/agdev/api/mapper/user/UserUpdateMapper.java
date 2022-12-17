package br.com.agdev.api.mapper.user;

import br.com.agdev.api.dto.user.UserDTOUpdate;
import br.com.agdev.api.mapper.DefaultMapper;
import br.com.agdev.domain.model.User;

public class UserUpdateMapper extends DefaultMapper<User, UserDTOUpdate> {

	public UserUpdateMapper() {
		super(User.class, UserDTOUpdate.class);
	}

}
