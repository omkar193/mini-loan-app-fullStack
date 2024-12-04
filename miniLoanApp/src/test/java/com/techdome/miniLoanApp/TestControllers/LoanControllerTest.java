package com.techdome.miniLoanApp.TestControllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.techdome.miniLoanApp.controller.LoanController;
import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.service.LoanService;


@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @Test
    void testCreateLoan() {
        Loan loan = new Loan();
        loan.setId("1");
        loan.setAmountRequired(5000);
        loan.setLoanTerm(12);
        
        when(loanService.createLoan(loan)).thenReturn(loan);

        ResponseEntity<Loan> response = loanController.createLoan(loan);

        assertNotNull(response);
        assertEquals(loan, response.getBody());
        verify(loanService, times(1)).createLoan(loan);
    }

    @Test
    void testGetLoansByUserId() {
        String userId = "user123";
        List<Loan> loans = Arrays.asList(new Loan(), new Loan());

        when(loanService.findLoansByUserId(userId)).thenReturn(loans);

        ResponseEntity<List<Loan>> response = loanController.getLoansByUserId(userId);

        assertNotNull(response);
        assertEquals(loans, response.getBody());
        verify(loanService, times(1)).findLoansByUserId(userId);
    }

    @Test
    void testApproveLoan() {
        String loanId = "loan123";
        Loan loan = new Loan();
        loan.setId(loanId);
        loan.setStatus("PENDING");

        when(loanService.findLoanById(loanId)).thenReturn(Optional.of(loan));
        when(loanService.updateLoan(loan)).thenReturn(loan);

        ResponseEntity<Loan> response = loanController.approveLoan(loanId);

        assertNotNull(response);
        assertEquals("APPROVED", response.getBody().getStatus());
        verify(loanService, times(1)).findLoanById(loanId);
        verify(loanService, times(1)).updateLoan(loan);
    }

  
    @Test
    void testGetAllPendingLoans() {
        List<Loan> loans = Arrays.asList(new Loan(), new Loan());

        when(loanService.findLoansByStatus("PENDING")).thenReturn(loans);

        ResponseEntity<List<Loan>> response = loanController.getAllPendingLoans();

        assertNotNull(response);
        assertEquals(loans, response.getBody());
        verify(loanService, times(1)).findLoansByStatus("PENDING");
    }
}

