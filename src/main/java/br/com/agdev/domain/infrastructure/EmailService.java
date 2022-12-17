package br.com.agdev.domain.infrastructure;

import org.springframework.stereotype.Service;

import br.com.agdev.domain.model.User;
import br.com.agdev.domain.services.CommunicationService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService implements CommunicationService {

	public EmailService() {
	}

	@Override
	public void communicate(User who, String subject, String body) {
		// TODO Implement communication by email

		log.info(String.format("Enviando e-mail para %s com assunto %s e corpo %s", who.getEmail(), subject, body));
		log.info(String.format("E-mail enviado com sucesso para %s", who.getEmail()));
	}
}
