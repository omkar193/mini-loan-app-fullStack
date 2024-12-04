package com.techdome.miniLoanApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.service.LoanService;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoanController {

	    @Autowired
	    private LoanService loanService;

	    @PostMapping("/create")
	    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
	        Loan createdLoan = loanService.createLoan(loan);
	        return ResponseEntity.ok(createdLoan);
	    }

	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Loan>> getLoansByUserId(@PathVariable String userId) {
	        List<Loan> loans = loanService.findLoansByUserId(userId);
	        return ResponseEntity.ok(loans);
	    }

	    @PatchMapping("/approve/{loanId}")
	    public ResponseEntity<Loan> approveLoan(@PathVariable String loanId) {
	        Loan loan = loanService.findLoanById(loanId).orElseThrow();
	        loan.setStatus("APPROVED");
	        Loan updatedLoan = loanService.updateLoan(loan);
	        return ResponseEntity.ok(updatedLoan);
	    }
	    
	    @GetMapping("/status/pending")
	    public ResponseEntity<List<Loan>> getAllPendingLoans() {
	        List<Loan> pendingLoans = loanService.findLoansByStatus("PENDING");
	        return ResponseEntity.ok(pendingLoans);
	    }
}
