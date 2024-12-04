package com.techdome.miniLoanApp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loans")
public class Loan {
	    @Id
	    private String id;
	    private String userId;
	    private String username; 
	    private double amountRequired;
	    private int loanTerm; 
	    private String status; 
	    private List<Repayment> repayments; 
	    
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public double getAmountRequired() {
			return amountRequired;
		}
		public void setAmountRequired(double amountRequired) {
			this.amountRequired = amountRequired;
		}
		public int getLoanTerm() {
			return loanTerm;
		}
		public void setLoanTerm(int loanTerm) {
			this.loanTerm = loanTerm;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public List<Repayment> getRepayments() {
			return repayments;
		}
		public void setRepayments(List<Repayment> repayments) {
			this.repayments = repayments;
		}
	    
	    
}
