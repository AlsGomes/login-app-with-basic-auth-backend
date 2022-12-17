package br.com.agdev.domain.services;

import br.com.agdev.domain.model.User;

public interface CommunicationService {

	void communicate(User who, String subject, String body);

}
