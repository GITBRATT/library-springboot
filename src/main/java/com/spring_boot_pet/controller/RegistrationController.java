package com.spring_boot_pet.controller;

import com.spring_boot_pet.model.RegistrationForm;
import com.spring_boot_pet.model.UserEntity;
import com.spring_boot_pet.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registrForm(Model model) {
        RegistrationForm form = new RegistrationForm();
        model.addAttribute("form", form);
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("form") RegistrationForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
