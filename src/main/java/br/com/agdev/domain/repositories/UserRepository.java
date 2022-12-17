package br.com.agdev.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agdev.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
