package com.example.LibraryManagementSystem.Dto.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookCreateRequest {
    @Size(min = 3,max=150,message = "Kitab adı minimum 3, maksimum 150 hərf təşkil etməlidir")
    private String bookName;
    @Size(min = 3,max=150,message = "Müəllif adı minimum 3, maksimum 150 hərf təşkil etməlidir")
    private String bookAuthor;
    @NotBlank(message="Zəhmət olmasa kateqoriya seçin")
    private String bookCategory;
    private int stock;
}