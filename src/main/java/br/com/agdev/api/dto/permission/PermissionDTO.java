package br.com.agdev.api.dto.permission;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class PermissionDTO {

	private Long id;
	private String name;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime lastUpdated;
}
