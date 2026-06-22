package com.spring_boot_pet.controller;

import com.spring_boot_pet.service.BookService;
import com.spring_boot_pet.service.LoanService;
import com.spring_boot_pet.service.ReaderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final BookService bookService;
    private final ReaderService readerService;

    public LoanController(LoanService loanService,
                          BookService bookService,
                          ReaderService readerService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    @GetMapping
    public String getLoans(Model model) {
        model.addAttribute("loans", loanService.findAll());
        return "loans";
    }

    @GetMapping("/add")
    public String showIssueForm(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("readers", readerService.findAll());
        return "loan-form";
    }

    @PostMapping("/add")
    public String issueLoan(@RequestParam Long bookId,
                            @RequestParam Long readerId) {
        loanService.issue(bookId, readerId);
        return "redirect:/loans";
    }

    @PostMapping("/{id}/return")
    public String returnLoan(@PathVariable Long id) {
        loanService.returnBook(id);
        return "redirect:/loans";
    }

    @PostMapping("/{id}/delete")
    public String deleteLoan(@PathVariable Long id) {
        loanService.deleteById(id);
        return "redirect:/loans";
    }
}
