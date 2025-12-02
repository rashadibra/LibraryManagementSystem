package com.example.LibraryManagementSystem.Dto.Book;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookCreateResponse{
    private int id;
    @NotBlank
    private String bookName;
    private String bookAuthor;
    private String bookCategory;
    private LocalDateTime createdAt;
    private int stock;
}