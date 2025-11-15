package com.example.LibraryManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_name", nullable = false, length = 100)
    private String book_name;

    @Column(name = "book_author", nullable = false, length = 150)
    private String book_author;

    @Column(updatable = false)
    private LocalDateTime created_id;
}