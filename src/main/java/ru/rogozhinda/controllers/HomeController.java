package ru.rogozhinda.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface HomeController {
    /**
     * Отображает главную страницу.
     */
    @GetMapping()
    String index(Model model);
}
