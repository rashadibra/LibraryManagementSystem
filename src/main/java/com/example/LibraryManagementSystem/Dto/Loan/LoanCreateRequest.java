package com.example.LibraryManagementSystem.Dto.Loan;

import com.example.LibraryManagementSystem.Enum.loanStatus;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanCreateRequest {
    private int userId;
    private int bookId;
    private Date dueDate;
}
