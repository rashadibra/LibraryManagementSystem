package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {
}
