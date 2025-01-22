package ru.rogozhinda.controllers;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@EnableCaching
public interface HomeController {
    /**
     * Отображает главную страницу.
     */
    @Cacheable("home")
    @GetMapping()
    String index(Model model);
}
