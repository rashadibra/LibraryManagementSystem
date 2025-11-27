package com.example.LibraryManagementSystem.Dto.Loan;

import com.example.LibraryManagementSystem.Enum.loanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanCreateResponse {
    private int loanId;
    private int userId;
    private int bookId;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
    private loanStatus status;
}
