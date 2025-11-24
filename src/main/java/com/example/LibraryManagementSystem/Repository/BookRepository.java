package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Dto.Book.BookCreateResponse;
import com.example.LibraryManagementSystem.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    public boolean existsByBookNameAndBookAuthor(String bookName,String bookAuthor);
    public Optional<BookEntity> findByBookNameAndBookAuthor(String bookName, String bookAuthor);
}