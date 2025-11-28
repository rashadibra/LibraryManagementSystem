package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Dto.Loan.LoanCreateRequest;
import com.example.LibraryManagementSystem.Dto.Loan.LoanUpdateStatus;
import com.example.LibraryManagementSystem.Entity.BookEntity;
import com.example.LibraryManagementSystem.Entity.LoanEntity;
import com.example.LibraryManagementSystem.Entity.UserEntity;
import com.example.LibraryManagementSystem.Enum.loanStatus;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Repository.LoanRepository;
import com.example.LibraryManagementSystem.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
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


    //Creating loan
    public String CreateLoan_Service(LoanCreateRequest loanBook) {


        // User tapılır, tapılmasa mesaj dönür
        Optional<UserEntity> userOpt = userRepository.findById(loanBook.getUserId());
        if (userOpt.isEmpty()) {
            return "İstifadəçi tapılmadı.";
        }

        // Book tapılır, tapılmasa mesaj dönür.
        Optional<BookEntity> bookOpt = bookRepository.findById(loanBook.getBookId());
        if (bookOpt.isEmpty()) {
            return "Kitab tapılmadı.";
        }

        //      Belə loan olub olmadığını yoxla
          Optional<LoanEntity> orderedloan=loanRepository.findByUserIdAndBookId(userOpt.get(), bookOpt.get());
          if(orderedloan.isPresent()&&orderedloan.get().getStatus()==loanStatus.ONGOING) {
              return "Bu kitab artıq istifadəçidə mövcuddur.";
          }

        loanRepository.save(LoanEntity.builder()
                .userId(userOpt.get())
                .bookId(bookOpt.get())
                .dueDate(loanBook.getDueDate())
                        .status(loanStatus.ONGOING)
                .build());

            return "Qeydə alındı.";
    }

//    Delete Loan
public boolean deleteLoanById_Service(int id){
    if (loanRepository.existsById(id)) {
        loanRepository.deleteById(id);
        return true;
    } else {
        return false;
    }
}



    //    Update Loan status
public boolean updateLoanStatus_Service(int loanId) {
    int updated = loanRepository.updateLoanStatus(
            loanId,
            loanStatus.RETURNED,
            LocalDate.now()  // returnedDate
    );
    return updated > 0; // true → yeniləndi, false → tapılmadı
}
}