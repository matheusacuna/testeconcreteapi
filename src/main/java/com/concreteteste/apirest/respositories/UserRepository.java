package com.concreteteste.apirest.respositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concreteteste.apirest.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByEmail(String email);

	Optional<User> findByToken(UUID fromString);

	Optional<User> findById(UUID fromString);
	
}
