package br.com.agdev.api.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime lastUpdated;
}
