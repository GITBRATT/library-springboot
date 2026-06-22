package com.spring_boot_pet.controller;

import com.spring_boot_pet.model.ReaderEntity;
import com.spring_boot_pet.service.ReaderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public String getReaders(Model model) {
        model.addAttribute("readers", readerService.findAll());
        return "readers";
    }

    @GetMapping("/{id}")
    public String getReaderById(@PathVariable Long id, Model model) {
        model.addAttribute("reader", readerService.findById(id));
        return "reader";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reader", new ReaderEntity());
        return "reader-form";
    }

    @PostMapping("/add")
    public String addReader(@Valid @ModelAttribute("reader") ReaderEntity reader,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reader-form";
        }
        readerService.save(reader);
        return "redirect:/readers";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("reader", readerService.findById(id));
        return "reader-form";
    }

    @PostMapping("/{id}/edit")
    public String updateReader(@PathVariable Long id,
                               @Valid @ModelAttribute("reader") ReaderEntity reader,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reader-form";
        }
        reader.setId(id);
        readerService.save(reader);
        return "redirect:/readers";
    }

    @PostMapping("/{id}/delete")
    public String deleteReader(@PathVariable Long id) {
        readerService.deleteById(id);
        return "redirect:/readers";
    }
}
