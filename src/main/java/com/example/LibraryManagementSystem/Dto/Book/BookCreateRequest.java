package com.example.LibraryManagementSystem.Dto.Book;

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

    private String bookName;
    private String bookAuthor;

}