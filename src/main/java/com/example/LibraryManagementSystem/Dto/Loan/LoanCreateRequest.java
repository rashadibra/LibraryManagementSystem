package com.example.LibraryManagementSystem.Dto.Loan;

import com.example.LibraryManagementSystem.Enum.loanStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanCreateRequest {
    private int userId;
    private int bookId;
    private LocalDate dueDate;
}
