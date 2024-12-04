package com.techdome.miniLoanApp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.techdome.miniLoanApp.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByUsername(String username);

	long countUsersByRole(String roleAdmin);

}
