package com.techdome.miniLoanApp.TestServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techdome.miniLoanApp.model.Loan;
import com.techdome.miniLoanApp.model.Repayment;
import com.techdome.miniLoanApp.repository.LoanRepository;
import com.techdome.miniLoanApp.service.RepaymentService;

@ExtendWith(MockitoExtension.class)
class RepaymentServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private RepaymentService repaymentService;

    private Loan loan;
    private Repayment repayment;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setStatus("APPROVED");
        repayment = new Repayment();
        repayment.setAmount(100.0);
        repayment.setStatus("PENDING");
    }


    @Test
    void testAddRepaymentLoanNotApproved() {
        loan.setStatus("PENDING");

        when(loanRepository.findById("1")).thenReturn(Optional.of(loan));

        Repayment repaymentRequest = new Repayment();
        repaymentRequest.setAmount(100.0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repaymentService.addRepayment("1", repaymentRequest);
        });

        assertEquals("Loan is not approved. Repayment cannot be made.", exception.getMessage());
        verify(loanRepository, never()).save(any());
    }

    
    @Test
    void testAddRepaymentLoanNotFound() {
        when(loanRepository.findById("1")).thenReturn(Optional.empty());

        Repayment repaymentRequest = new Repayment();
        repaymentRequest.setAmount(100.0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repaymentService.addRepayment("1", repaymentRequest);
        });

        assertEquals("Loan not found", exception.getMessage());
    }

    @Test
    void testUpdateLoanStatusAllPaid() {
        Loan loanWithAllRepaymentsPaid = new Loan();
        loanWithAllRepaymentsPaid.setStatus("APPROVED");
        Repayment rep1 = new Repayment();
        rep1.setStatus("PAID");
        Repayment rep2 = new Repayment();
        rep2.setStatus("PAID");
        loanWithAllRepaymentsPaid.setRepayments(List.of(rep1, rep2));

        repaymentService.updateLoanStatus(loanWithAllRepaymentsPaid);

        assertEquals("PAID", loanWithAllRepaymentsPaid.getStatus());
    }

    @Test
    void testUpdateLoanStatusNotAllPaid() {
        Loan loanWithPendingRepayment = new Loan();
        loanWithPendingRepayment.setStatus("APPROVED");
        Repayment rep1 = new Repayment();
        rep1.setStatus("PAID");
        Repayment rep2 = new Repayment();
        rep2.setStatus("PENDING");
        loanWithPendingRepayment.setRepayments(List.of(rep1, rep2));

        repaymentService.updateLoanStatus(loanWithPendingRepayment);

        assertNotEquals("PAID", loanWithPendingRepayment.getStatus());
    }
}
