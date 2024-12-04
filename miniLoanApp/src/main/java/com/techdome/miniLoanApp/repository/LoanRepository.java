package com.techdome.miniLoanApp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.techdome.miniLoanApp.model.Loan;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String>{
   
	List<Loan> findByUserId(String userId);
	
	List<Loan> findByStatus(String status);
}
