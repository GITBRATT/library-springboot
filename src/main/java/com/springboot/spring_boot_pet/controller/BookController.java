package com.springboot.spring_boot_pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/library")
public class BookController
{
    @GetMapping
    public String getBook(){
        return "books";
    }
}
