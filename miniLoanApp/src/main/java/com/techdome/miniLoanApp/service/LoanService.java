package com.techdome.miniLoanApp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.model.Repayment;
import com.techdome.miniLoanApp.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
    private LoanRepository loanRepository;
	
	  public Loan createLoan(Loan loan) {	    
	        if (loan.getStatus() == null) {
	            loan.setStatus("PENDING");
	        }
	        double weeklyAmount = loan.getAmountRequired() / loan.getLoanTerm();
	        List<Repayment> repayments = calculateRepayments(loan.getLoanTerm(), weeklyAmount);
	        loan.setRepayments(repayments);
	        return loanRepository.save(loan);
	    }
	  
	    private List<Repayment> calculateRepayments(int loanTerm, double weeklyAmount) {
	        List<Repayment> repayments = new ArrayList<>();
	        LocalDate currentDate = LocalDate.now();
	        for (int i = 0; i < loanTerm; i++) {
	            Repayment repayment = new Repayment();
	            repayment.setAmount(weeklyAmount);
	            repayment.setDueDate(currentDate.plusWeeks(i+1)); 
	            repayment.setStatus("PENDING"); 

	            repayments.add(repayment);
	        }

	        return repayments;
	    }

    public List<Loan> findLoansByUserId(String userId) {
        return loanRepository.findByUserId(userId);
    }

    public Optional<Loan> findLoanById(String loanId) {
        return loanRepository.findById(loanId);
    }

    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }
    
    public List<Loan> findLoansByStatus(String status) {
        return loanRepository.findByStatus(status);
    }
}
