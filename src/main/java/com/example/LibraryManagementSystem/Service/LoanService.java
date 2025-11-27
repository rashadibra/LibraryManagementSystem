package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Dto.Loan.LoanCreateRequest;
import com.example.LibraryManagementSystem.Entity.BookEntity;
import com.example.LibraryManagementSystem.Entity.LoanEntity;
import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Enum.loanStatus;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Repository.LoanRepository;
import com.example.LibraryManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Optional;

@Service
public class LoanService {
    private LoanRepository loanRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository,
                       UserRepository userRepository,
                       BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    //Creating loaned book
    public String CreateLoan_Service(LoanCreateRequest loanBook) {

        // User tapılır, tapılmasa mesaj dönülür
        Optional<UserEntity> userOpt = userRepository.findById(loanBook.getUserId());
        if (userOpt.isEmpty()) {
            return "İstifadəçi tapılmadı.";
        }

        // Book tapılır, tapılmasa mesaj dönülür.
        Optional<BookEntity> bookOpt = bookRepository.findById(loanBook.getBookId());
        if (bookOpt.isEmpty()) {
            return "Kitab tapılmadı.";
        }

        loanRepository.save(LoanEntity.builder()
                .userId(userOpt.get())
                .bookId(bookOpt.get())
                .dueDate(loanBook.getDueDate())
                        .status(loanStatus.ONGOING)
                .build());

            return "Qeydə alındı.";

    }
}