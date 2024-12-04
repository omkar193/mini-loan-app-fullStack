package com.techdome.miniLoanApp.TestModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.techdome.miniLoanApp.model.Repayment;

class RepaymentTest {

    @Test
    void testDefaultConstructor() {
        Repayment repayment = new Repayment();
        assertNotNull(repayment);
    }

    @Test
    void testSetAndGetId() {
        Repayment repayment = new Repayment();
        String id = "repaymentId123";
        repayment.setId(id);
        assertEquals(id, repayment.getId());
    }

    @Test
    void testSetAndGetDueDate() {
        Repayment repayment = new Repayment();
        LocalDate dueDate = LocalDate.of(2023, 12, 1);
        repayment.setDueDate(dueDate);
        assertEquals(dueDate, repayment.getDueDate());
    }

    @Test
    void testSetAndGetAmount() {
        Repayment repayment = new Repayment();
        double amount = 1000.00;
        repayment.setAmount(amount);
        assertEquals(amount, repayment.getAmount(), 0.001);
    }

    @Test
    void testSetAndGetStatus() {
        Repayment repayment = new Repayment();
        String status = "PENDING";
        repayment.setStatus(status);
        assertEquals(status, repayment.getStatus());
    }
}

