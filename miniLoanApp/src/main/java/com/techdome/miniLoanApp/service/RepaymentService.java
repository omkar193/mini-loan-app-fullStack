package com.techdome.miniLoanApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.model.Repayment;
import com.techdome.miniLoanApp.repository.LoanRepository;

@Service
public class RepaymentService {

	   @Autowired
	    private LoanRepository loanRepository;
	   
	   public Loan addRepayment(String loanId, Repayment repaymentRequest) {
	  
	        Loan loan = loanRepository.findById(loanId)
	                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

	        
	        if (!loan.getStatus().equals("APPROVED")) {
	            throw new IllegalArgumentException("Loan is not approved. Repayment cannot be made.");
	        }

	        
	        Repayment nextRepayment = loan.getRepayments().stream()
	                .filter(r -> r.getStatus().equals("PENDING"))
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException("No pending repayments for this loan"));

	        
	        if (repaymentRequest.getAmount() < nextRepayment.getAmount()) {
	            throw new IllegalArgumentException("Repayment amount is less than the scheduled repayment amount");
	        }

	        
	        nextRepayment.setStatus("PAID");
	        
	        boolean allPaid = loan.getRepayments().stream()
	                .allMatch(r -> r.getStatus().equals("PAID"));

	        if (allPaid) {
	            loan.setStatus("PAID"); 
	        }	        
	        return loanRepository.save(loan);
	    }

	    public void updateLoanStatus(Loan loan) {
	        boolean allPaid = loan.getRepayments().stream().allMatch(r -> r.getStatus().equals("PAID"));
	        if (allPaid) {
	            loan.setStatus("PAID");
	        }
	    }
}
