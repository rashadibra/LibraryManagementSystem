package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Dto.Book.BookCreateRequest;
import com.example.LibraryManagementSystem.Dto.Book.BookCreateResponse;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //    Create book
    @PostMapping
    public ResponseEntity createBook(@RequestBody BookCreateRequest book) {
        Optional<BookCreateResponse> createdBook = bookService.createBook_Service(book);
        if (createdBook.isPresent()) {
            return ResponseEntity.ok().body(createdBook);
        } else {
            return ResponseEntity.status(409).body(null);
        }
    }

    //    Find All Book
    @GetMapping("allBooks")
    public List<BookCreateResponse> FindAllBook() {
        return bookService.findAllBook_Service();
    }

    //    Find Book By Id
    @GetMapping("/{id}")
    public ResponseEntity<BookCreateResponse> findUserById(@PathVariable int id) {
        return bookService.findBookById_Service(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Find Book By Name
    @GetMapping("/by-na")
    public ResponseEntity<BookCreateResponse> findUsersByNameAndSurname(
            @RequestParam String bookName,
            @RequestParam String bookAuthor
    ) {
        Optional<BookCreateResponse> book = bookService.findBookByNameAndAuthor_Service(bookName, bookAuthor);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book.get());
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

    //***    Increment in stock   ***//
    @PutMapping("/{id}/updateStock")
    public ResponseEntity<String> incrementStockById(
            @PathVariable int id,
            @RequestParam int stockQuantity
    ) {
     boolean updatedStock=  bookService.updateStock_Service(id,stockQuantity);
        if(updatedStock){
            return ResponseEntity.ok("Stok sayı yeniləndi.");
        }
        else{
            return ResponseEntity.status(400).body("Stok sayı yenilənmədi.");
        }
    }

}
