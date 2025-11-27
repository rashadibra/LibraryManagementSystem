package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Dto.Loan.LoanCreateRequest;
import com.example.LibraryManagementSystem.Dto.Loan.LoanCreateResponse;
import com.example.LibraryManagementSystem.Service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

//    Create Loaned book
@PostMapping
    public ResponseEntity<String> CreateLoan(@RequestBody LoanCreateRequest loanBook){
        String savedLoan=loanService.CreateLoan_Service(loanBook);
        return ResponseEntity.ok(savedLoan);
}
}
