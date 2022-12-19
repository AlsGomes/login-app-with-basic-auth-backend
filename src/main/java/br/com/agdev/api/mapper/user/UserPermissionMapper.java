package br.com.agdev.api.mapper.user;

import org.springframework.beans.BeanUtils;

import br.com.agdev.api.dto.user.UserDTOPermission;
import br.com.agdev.api.mapper.DefaultMapper;
import br.com.agdev.api.mapper.permission.PermissionSummaryMapper;
import br.com.agdev.domain.model.User;

public class UserPermissionMapper extends DefaultMapper<User, UserDTOPermission> {

	private PermissionSummaryMapper permissionMapper = new PermissionSummaryMapper();
	
	public UserPermissionMapper() {
		super(User.class, UserDTOPermission.class);
	}

	@Override
	public UserDTOPermission toDTO(User entity) {
		UserDTOPermission dto = new UserDTOPermission();
		BeanUtils.copyProperties(entity, dto);
		dto.setPermissions(permissionMapper.toDTOList(entity.getPermissions()));
		return dto;
	}
}
