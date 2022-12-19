package br.com.agdev.api.mapper.permission;

import br.com.agdev.api.dto.permission.PermissionDTO;
import br.com.agdev.api.mapper.DefaultMapper;
import br.com.agdev.domain.model.Permission;

public class PermissionMapper extends DefaultMapper<Permission, PermissionDTO> {

	public PermissionMapper() {
		super(Permission.class, PermissionDTO.class);
	}
}
