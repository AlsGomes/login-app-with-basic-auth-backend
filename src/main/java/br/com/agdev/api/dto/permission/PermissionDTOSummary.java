package br.com.agdev.api.dto.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class PermissionDTOSummary {

	private Long id;
	private String name;
}
