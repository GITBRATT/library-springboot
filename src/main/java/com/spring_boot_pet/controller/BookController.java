package com.spring_boot_pet.controller;

import com.spring_boot_pet.model.BookEntity;
import com.spring_boot_pet.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class BookController
{
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute(
                "books",
                bookService.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String getBooksById(@PathVariable Long id, Model model) {
        model.addAttribute(
                "book",
                bookService.findById(id));
        return "book";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new BookEntity());
        return "book-form";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") BookEntity book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-form";
        }
        bookService.save(book);
        return "redirect:/library";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book-form";
    }

    @PostMapping("/{id}/edit")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookEntity book) {
        book.setId(id);
        bookService.save(book);
        return "redirect:/library";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/library";
    }
}
