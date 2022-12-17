package br.com.agdev.api.mapper.user;

import br.com.agdev.api.dto.UserDTOInput;
import br.com.agdev.api.mapper.DefaultMapper;
import br.com.agdev.domain.model.User;

public class UserInputMapper extends DefaultMapper<User, UserDTOInput> {

	public UserInputMapper() {
		super(User.class, UserDTOInput.class);
	}

}
