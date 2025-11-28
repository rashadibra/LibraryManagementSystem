package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Dto.Loan.LoanCreateRequest;
import com.example.LibraryManagementSystem.Dto.Loan.LoanCreateResponse;
import com.example.LibraryManagementSystem.Dto.Loan.LoanUpdateStatus;
import com.example.LibraryManagementSystem.Service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    //    Create Loaned book
    @PostMapping
    public ResponseEntity<String> CreateLoan(@RequestBody LoanCreateRequest loanBook) {
        String savedLoan = loanService.CreateLoan_Service(loanBook);
        return ResponseEntity.ok(savedLoan);
    }


    //Update Loan book status (Returned)
    @PatchMapping("/{loanId}/returned")
    public ResponseEntity<String> returnLoan(@PathVariable int loanId) {

        boolean success = loanService.updateLoanStatus_Service(loanId);

        if (success) {
            return ResponseEntity.ok("Loan status RETURNED olaraq dəyişdirildi və returnedDate təyin olundu.");
        } else {
            return ResponseEntity.status(404).body("Loan tapılmadı.");
        }
    }
}
