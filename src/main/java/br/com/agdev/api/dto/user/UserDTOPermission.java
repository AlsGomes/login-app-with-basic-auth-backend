package br.com.agdev.api.dto.user;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.agdev.api.dto.permission.PermissionDTOSummary;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDTOPermission {
	
	private Long id;
	private String name;
	private String email;
	private List<PermissionDTOSummary> permissions = new ArrayList<>();


}
