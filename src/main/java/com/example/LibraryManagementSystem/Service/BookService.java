package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Dto.Book.BookCreateRequest;
import com.example.LibraryManagementSystem.Dto.Book.BookCreateResponse;
import com.example.LibraryManagementSystem.Dto.User.UserCreateResponse;
import com.example.LibraryManagementSystem.Entity.BookEntity;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //    Create Book
    public BookCreateResponse createBook_Service(BookCreateRequest book) {
        BookEntity savedBook = bookRepository.save(
                BookEntity.builder()
                        .bookName(book.getBookName())
                        .bookAuthor(book.getBookAuthor())
                        .build());
        return new BookCreateResponse(savedBook.getId(), savedBook.getBookName(), savedBook.getBookAuthor(), savedBook.getCreatedAt());
    }

    ;

    //    Find ALl Books
    public List<BookCreateResponse> findAllBook_Service() {
        return bookRepository.findAll().stream().map(book -> {
            return new BookCreateResponse(book.getId(), book.getBookName(), book.getBookAuthor(), book.getCreatedAt());
        }).collect(Collectors.toList());
    }

    //    Find book by id
    public Optional<BookCreateResponse> findBookById_Service(int id) {
        return bookRepository.findById(id).map(book -> {
            return new BookCreateResponse(book.getId(), book.getBookName(), book.getBookAuthor(), book.getCreatedAt());
        });
    }


    //    Delete book by id
    public boolean deleteBookById_Service(int id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

}
