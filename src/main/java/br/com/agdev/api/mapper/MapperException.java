package br.com.agdev.api.mapper;

public class MapperException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapperException(String message) {
		super(message);
	}
}
