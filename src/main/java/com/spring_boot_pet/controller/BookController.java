package com.spring_boot_pet.controller;

import com.spring_boot_pet.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
