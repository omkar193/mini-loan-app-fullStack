package com.techdome.miniLoanApp.TestControllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.techdome.miniLoanApp.controller.RepaymentController;
import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.model.Repayment;
import com.techdome.miniLoanApp.service.RepaymentService;

@ExtendWith(MockitoExtension.class)
class RepaymentControllerTest {

    @Mock
    private RepaymentService repaymentService;

    @InjectMocks
    private RepaymentController repaymentController;

    @Test
    void testAddRepayment_Success() {
        String loanId = "loan123";
        Repayment repaymentRequest = new Repayment();
        repaymentRequest.setAmount(1000.0);

        Loan updatedLoan = new Loan();
        updatedLoan.setId(loanId);
        updatedLoan.setStatus("APPROVED");

        when(repaymentService.addRepayment(anyString(), any(Repayment.class))).thenReturn(updatedLoan);

        ResponseEntity<Loan> response = repaymentController.addRepayment(loanId, repaymentRequest);

        assertNotNull(response);
        assertEquals(updatedLoan, response.getBody());
        verify(repaymentService, times(1)).addRepayment(loanId, repaymentRequest);
    }

    @Test
    void testAddRepayment_LoanNotFound() {
        String loanId = "invalidLoanId";
        Repayment repaymentRequest = new Repayment();
        repaymentRequest.setAmount(1000.0);

        when(repaymentService.addRepayment(anyString(), any(Repayment.class)))
                .thenThrow(new IllegalArgumentException("Loan not found"));

        try {
            repaymentController.addRepayment(loanId, repaymentRequest);
        } catch (Exception e) {
            assertEquals("Loan not found", e.getMessage());
        }

        verify(repaymentService, times(1)).addRepayment(loanId, repaymentRequest);
    }

    @Test
    void testAddRepayment_InsufficientRepaymentAmount() {
        String loanId = "loan123";
        Repayment repaymentRequest = new Repayment();
        repaymentRequest.setAmount(500.0);

        when(repaymentService.addRepayment(anyString(), any(Repayment.class)))
                .thenThrow(new IllegalArgumentException("Repayment amount is less than the scheduled repayment amount"));

        try {
            repaymentController.addRepayment(loanId, repaymentRequest);
        } catch (Exception e) {
            assertEquals("Repayment amount is less than the scheduled repayment amount", e.getMessage());
        }

        verify(repaymentService, times(1)).addRepayment(loanId, repaymentRequest);
    }
}

