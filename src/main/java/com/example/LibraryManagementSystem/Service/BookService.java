package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Dto.Book.BookCreateRequest;
import com.example.LibraryManagementSystem.Dto.Book.BookCreateResponse;
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
    public Optional<BookCreateResponse> createBook_Service(BookCreateRequest book) {
        if(!bookRepository.existsByBookNameAndBookAuthor(book.getBookName(),book.getBookAuthor())){
            BookEntity savedBook = bookRepository.save(
                    BookEntity.builder()
                            .bookName(book.getBookName())
                            .bookAuthor(book.getBookAuthor())
                            .bookCatgory(book.getBookCategory())
                            .stock(1)
                            .build());
            return Optional.of(new BookCreateResponse(savedBook.getId(), savedBook.getBookName(), savedBook.getBookAuthor(), savedBook.getBookCatgory(), savedBook.getCreatedAt(),savedBook.getStock()));
        }
return Optional.empty();
    }

    ;

    //    Find ALl Books
    public List<BookCreateResponse> findAllBook_Service() {
        return bookRepository.findAll().stream().map(book -> {
            return new BookCreateResponse(book.getId(), book.getBookName(), book.getBookAuthor(), book.getBookCatgory() ,book.getCreatedAt(), book.getStock());
        }).collect(Collectors.toList());
    }

    //    Find book by id
    public Optional<BookCreateResponse> findBookById_Service(int id) {
        return bookRepository.findById(id).map(book -> {
            return new BookCreateResponse(book.getId(), book.getBookName(), book.getBookAuthor(), book.getBookCatgory(), book.getCreatedAt(), book.getStock());
        });
    }

//    Find book by Name and Author
    public Optional<BookCreateResponse> findBookByNameAndAuthor_Service(String bookName, String bookAuthor){
        return bookRepository.findByBookNameAndBookAuthor(bookName,bookAuthor).map(book->{
           return new BookCreateResponse(book.getId(),book.getBookName(),book.getBookAuthor(),book.getBookCatgory() , book.getCreatedAt(),book.getStock());
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

    //***    Increment stock by id  ***//
    public boolean updateStock_Service(int id, int stock){
        int updated=bookRepository.updateStockById(id,stock);
       return updated>0;
    }


}
