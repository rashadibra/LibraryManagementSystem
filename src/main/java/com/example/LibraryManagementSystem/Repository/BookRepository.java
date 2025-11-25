package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Dto.Book.BookCreateResponse;
import com.example.LibraryManagementSystem.Entity.BookEntity;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    public boolean existsByBookNameAndBookAuthor(String bookName,String bookAuthor);
    public Optional<BookEntity> findByBookNameAndBookAuthor(String bookName, String bookAuthor);
//    update Book Stock
    @Transactional
    @Modifying
    @Query("UPDATE BookEntity book SET book.stock=:amount WHERE book.id=:id")
    public int updateStockById(@Param("id") int id, @Param("amount") int amount);

}