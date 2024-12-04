package com.techdome.miniLoanApp.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Repayment {

    @Id
    private String id;
    private LocalDate dueDate;
    private double amount;
    private String status; 
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
   public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    
}
