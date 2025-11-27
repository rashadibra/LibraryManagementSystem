package com.example.LibraryManagementSystem.Entity;

import com.example.LibraryManagementSystem.Enum.loanStatus;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "loaned_books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loan_id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;
    @ManyToOne
    @JoinColumn(name = "book_id",  nullable = false)
    private BookEntity bookId;
    @Column(name = "borrowed_date", insertable = false)
    private LocalDate borrowedDate;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    @Column(name = "returned_date")
    private LocalDate returnedDate;
    @Enumerated(EnumType.STRING )
    private loanStatus status;
}
