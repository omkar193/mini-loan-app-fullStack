package com.techdome.miniLoanApp.TestServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.repository.LoanRepository;
import com.techdome.miniLoanApp.service.LoanService;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private Loan loan;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setId("1");
        loan.setUserId("user1");
        loan.setAmountRequired(10000);
        loan.setLoanTerm(3);
        loan.setStatus("PENDING");
    }

    @Test
    void testCreateLoan() {
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan createdLoan = loanService.createLoan(loan);

        assertNotNull(createdLoan);
        assertEquals("PENDING", createdLoan.getStatus());
        assertEquals(3, createdLoan.getRepayments().size());  // 3 repayments for a 3-week loan
        verify(loanRepository, times(1)).save(loan);
    }

 
    @Test
    void testFindLoansByUserId() {
        when(loanRepository.findByUserId("user1")).thenReturn(Arrays.asList(loan));

        List<Loan> loans = loanService.findLoansByUserId("user1");

        assertNotNull(loans);
        assertEquals(1, loans.size());
        assertEquals("user1", loans.get(0).getUserId());
        verify(loanRepository, times(1)).findByUserId("user1");
    }

    @Test
    void testFindLoanById() {
        when(loanRepository.findById("1")).thenReturn(Optional.of(loan));

        Optional<Loan> foundLoan = loanService.findLoanById("1");

        assertTrue(foundLoan.isPresent());
        assertEquals("1", foundLoan.get().getId());
        verify(loanRepository, times(1)).findById("1");
    }

    @Test
    void testFindLoanByIdNotFound() {
        when(loanRepository.findById("1")).thenReturn(Optional.empty());

        Optional<Loan> foundLoan = loanService.findLoanById("1");

        assertFalse(foundLoan.isPresent());
        verify(loanRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateLoan() {
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan updatedLoan = loanService.updateLoan(loan);

        assertNotNull(updatedLoan);
        assertEquals("PENDING", updatedLoan.getStatus());
        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    void testFindLoansByStatus() {
        when(loanRepository.findByStatus("PENDING")).thenReturn(Arrays.asList(loan));

        List<Loan> loans = loanService.findLoansByStatus("PENDING");

        assertNotNull(loans);
        assertEquals(1, loans.size());
        assertEquals("PENDING", loans.get(0).getStatus());
        verify(loanRepository, times(1)).findByStatus("PENDING");
    }
}
