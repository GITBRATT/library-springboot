package com.spring_boot_pet.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public void exceptionHandler(Exception ex, Model model) {
        model.addAttribute("msg", ex.getMessage());
    }
}
