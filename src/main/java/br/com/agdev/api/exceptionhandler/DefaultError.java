package br.com.agdev.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class DefaultError {

	private LocalDateTime timestamp;
	private Integer status;
	private String type;
	private String title;
	private String detail;

	private List<Object> objects;

	@Getter
	@Builder
	public static class Object {
		private String propertyName;
		private String message;
	}
}
