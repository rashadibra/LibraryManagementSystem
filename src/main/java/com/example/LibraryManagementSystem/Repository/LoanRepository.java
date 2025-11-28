package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Entity.BookEntity;
import com.example.LibraryManagementSystem.Entity.LoanEntity;
import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Enum.loanStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {
    public Optional<LoanEntity> findByUserIdAndBookId(UserEntity userId, BookEntity bookId);
    @Modifying
    @Transactional
    @Query("UPDATE LoanEntity l SET l.status = :status, l.returnedDate = :returnedDate WHERE l.loan_id = :loanId")
    int updateLoanStatus(@Param("loanId") int loanId,
                         @Param("status") loanStatus status,
                         @Param("returnedDate") LocalDate returnedDate);
}
