package com.spring_boot_pet.controller;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

    @RequestMapping("/error")
    public String error() {
        return "error-page";
    }

    @ExceptionHandler
    public String exceptionHandler(Exception ex, Model model) {
        model.addAttribute("msg", ex.getMessage());
        return "redirect:/error-page";
    }
}
