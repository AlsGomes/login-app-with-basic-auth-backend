package br.com.agdev.api.mapper.permission;

import br.com.agdev.api.dto.permission.PermissionDTOSummary;
import br.com.agdev.api.mapper.DefaultMapper;
import br.com.agdev.domain.model.Permission;

public class PermissionSummaryMapper extends DefaultMapper<Permission, PermissionDTOSummary> {

	public PermissionSummaryMapper() {
		super(Permission.class, PermissionDTOSummary.class);
	}
}
