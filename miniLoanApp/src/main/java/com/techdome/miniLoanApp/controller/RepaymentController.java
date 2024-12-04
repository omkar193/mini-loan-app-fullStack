package com.techdome.miniLoanApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.model.Repayment;
import com.techdome.miniLoanApp.service.RepaymentService;

@RestController
@RequestMapping("/api/repayments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RepaymentController {

	    @Autowired
	    private RepaymentService repaymentService;

//	    @PostMapping("/{loanId}")
//	    public ResponseEntity<Loan> addRepayment(@PathVariable String loanId, @RequestBody Repayment repayment) {
//	        Loan updatedLoan = repaymentService.addRepayment(loanId, repayment);
//	        return ResponseEntity.ok(updatedLoan);
//	    }
	    
	    @PostMapping("/{loanId}")
	    public ResponseEntity<Loan> addRepayment(
	            @PathVariable String loanId,
	            @RequestBody Repayment repaymentRequest) {
	        Loan updatedLoan = repaymentService.addRepayment(loanId, repaymentRequest);
	        return ResponseEntity.ok(updatedLoan);
	    }
}
