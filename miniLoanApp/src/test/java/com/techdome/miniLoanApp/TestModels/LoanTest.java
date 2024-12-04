package com.techdome.miniLoanApp.TestModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.model.Repayment;

class LoanTest {

    @Test
    void testDefaultConstructor() {
        Loan loan = new Loan();
        assertNotNull(loan);
    }

    @Test
    void testSetAndGetId() {
        Loan loan = new Loan();
        String id = "loanId123";
        loan.setId(id);
        assertEquals(id, loan.getId());
    }

    @Test
    void testSetAndGetUserId() {
        Loan loan = new Loan();
        String userId = "userId123";
        loan.setUserId(userId);
        assertEquals(userId, loan.getUserId());
    }

    @Test
    void testSetAndGetUsername() {
        Loan loan = new Loan();
        String username = "testUser";
        loan.setUsername(username);
        assertEquals(username, loan.getUsername());
    }

    @Test
    void testSetAndGetAmountRequired() {
        Loan loan = new Loan();
        double amountRequired = 5000.00;
        loan.setAmountRequired(amountRequired);
        assertEquals(amountRequired, loan.getAmountRequired(), 0.001);
    }

    @Test
    void testSetAndGetLoanTerm() {
        Loan loan = new Loan();
        int loanTerm = 12;
        loan.setLoanTerm(loanTerm);
        assertEquals(loanTerm, loan.getLoanTerm());
    }

    @Test
    void testSetAndGetStatus() {
        Loan loan = new Loan();
        String status = "PENDING";
        loan.setStatus(status);
        assertEquals(status, loan.getStatus());
    }

    @Test
    void testSetAndGetRepayments() {
        Loan loan = new Loan();
        List<Repayment> repayments = new ArrayList<>();
        Repayment repayment = new Repayment();
        repayment.setAmount(1000.00);
        repayment.setStatus("PENDING");
        repayments.add(repayment);

        loan.setRepayments(repayments);

        assertNotNull(loan.getRepayments());
        assertEquals(1, loan.getRepayments().size());
        assertEquals(1000.00, loan.getRepayments().get(0).getAmount(), 0.001);
        assertEquals("PENDING", loan.getRepayments().get(0).getStatus());
    }
}

