package com.spring_boot_pet.service;

import com.spring_boot_pet.model.BookEntity;
import com.spring_boot_pet.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public BookEntity findById(Long id) {
        return bookRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Book not found")
        );
    }
}
