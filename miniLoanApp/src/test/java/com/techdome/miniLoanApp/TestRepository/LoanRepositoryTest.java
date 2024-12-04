package com.techdome.miniLoanApp.TestRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.repository.LoanRepository;

@SpringBootTest
class LoanRepositoryTest {

    @Mock
    private LoanRepository loanRepository;

    private Loan loan;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setId("1");
        loan.setUserId("user123");
        loan.setAmountRequired(5000);
        loan.setLoanTerm(12);
        loan.setStatus("PENDING");
    }

    @Test
    void testFindByUserId() {
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);

        when(loanRepository.findByUserId("user123")).thenReturn(loans);

        List<Loan> returnedLoans = loanRepository.findByUserId("user123");

        assertNotNull(returnedLoans);
        assertEquals(1, returnedLoans.size());
        assertEquals("user123", returnedLoans.get(0).getUserId());
        verify(loanRepository, times(1)).findByUserId("user123");
    }

    @Test
    void testFindByStatus() {
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);

        when(loanRepository.findByStatus("PENDING")).thenReturn(loans);

        List<Loan> returnedLoans = loanRepository.findByStatus("PENDING");

        assertNotNull(returnedLoans);
        assertEquals(1, returnedLoans.size());
        assertEquals("PENDING", returnedLoans.get(0).getStatus());
        verify(loanRepository, times(1)).findByStatus("PENDING");
    }

    @Test
    void testFindByUserIdNotFound() {
        List<Loan> loans = new ArrayList<>();

        when(loanRepository.findByUserId("user999")).thenReturn(loans);

        List<Loan> returnedLoans = loanRepository.findByUserId("user999");

        assertTrue(returnedLoans.isEmpty());
        verify(loanRepository, times(1)).findByUserId("user999");
    }

    @Test
    void testFindByStatusNotFound() {
        List<Loan> loans = new ArrayList<>();

        when(loanRepository.findByStatus("APPROVED")).thenReturn(loans);

        List<Loan> returnedLoans = loanRepository.findByStatus("APPROVED");

        assertTrue(returnedLoans.isEmpty());
        verify(loanRepository, times(1)).findByStatus("APPROVED");
    }
}

