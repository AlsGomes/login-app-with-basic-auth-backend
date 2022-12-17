package br.com.agdev.domain.exceptions;

public class UnathorizedDataAccessException extends DomainException {
	private static final long serialVersionUID = 1L;

	public UnathorizedDataAccessException(String message) {
		super(message);
	}

	public UnathorizedDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
