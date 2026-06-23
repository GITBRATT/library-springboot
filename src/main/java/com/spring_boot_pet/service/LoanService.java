package com.spring_boot_pet.service;

import com.spring_boot_pet.model.BookEntity;
import com.spring_boot_pet.model.LoanEntity;
import com.spring_boot_pet.model.ReaderEntity;
import com.spring_boot_pet.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    // Срок выдачи книги по умолчанию (в днях).
    private static final int LOAN_PERIOD_DAYS = 14;

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final ReaderService readerService;

    public LoanService(LoanRepository loanRepository,
                       BookService bookService,
                       ReaderService readerService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    public List<LoanEntity> findAll() {
        return loanRepository.findAll();
    }

    public LoanEntity findById(Long id) {
        return loanRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Запись о выдачи не найдена")
        );
    }

    // Выдать книгу читателю: создаём новую запись о выдачи.
    public LoanEntity issue(Long bookId, Long readerId) {
        BookEntity book = bookService.findById(bookId);
        ReaderEntity reader = readerService.findById(readerId);

        LoanEntity loan = new LoanEntity();
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(LOAN_PERIOD_DAYS));

        return loanRepository.save(loan);
    }

    // Вернуть книгу: проставляем фактическую дату возврата.
    public LoanEntity returnBook(Long id) {
        LoanEntity loan = findById(id);
        loan.setReturnedDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    public void deleteById(Long id) {
        loanRepository.deleteById(id);
    }

}
