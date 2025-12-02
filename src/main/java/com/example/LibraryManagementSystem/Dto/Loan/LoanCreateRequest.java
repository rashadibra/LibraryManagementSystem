package com.example.LibraryManagementSystem.Dto.Loan;

import com.example.LibraryManagementSystem.Enum.loanStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanCreateRequest {
    @NotBlank(message = "İstifadəi İd-si boşdur")
    private int userId;
    @NotBlank(message = "Kitab İd-si boşdur")
    private int bookId;
    @NotBlank(message = "Tarix qeyd edin")
    @Future(message = "Gələcək tarix qeyd edin")
    private LocalDate dueDate;
}
