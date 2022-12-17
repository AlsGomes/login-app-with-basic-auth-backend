package br.com.agdev.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErrorType {

	OBJECT_NOT_FOUND("/objeto-nao-encontrado", "Objeto não encontrado"),
	OBJECT_IN_USE("/objeto-em-uso", "Objeto em uso"),
	MESSAGE_NOT_READABLE("/payload-invalido", "Payload inválido"),
	UNATHORIZED_DATA_ACCESS("/acesso-dados-nao-autorizado", "Acesso aos dados não autorizado"),
	INVALID_AUTHENTICATION("/invalid-authentication", "Autenticação inválida"),
	INVALID_DATA("/dados-invalido", "Dados inválido");

	private String title;
	private String uri;

	private ErrorType(String path, String title) {
		this.title = title;
		this.uri = "http://login-app.com" + path;
	}

}
