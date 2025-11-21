package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Dto.Book.BookCreateRequest;
import com.example.LibraryManagementSystem.Dto.Book.BookCreateResponse;
import com.example.LibraryManagementSystem.Dto.User.UserCreateResponse;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }

//    Create book
    @PostMapping
    public ResponseEntity createBook(@RequestBody BookCreateRequest book){
        BookCreateResponse createdBook=bookService.createBook_Service(book);
        return ResponseEntity.ok().body(createdBook);
    }

//    Find All Book
    @GetMapping("allBooks")
    public List<BookCreateResponse> FindAllBook(){
        return bookService.findAllBook_Service();
    }

    //    Find Book By Id
    @GetMapping("/{id}")
    public ResponseEntity<BookCreateResponse> findUserById(@PathVariable int id) {
        return bookService.findBookById_Service(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id) {
        boolean deleted = bookService.deleteBookById_Service(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
