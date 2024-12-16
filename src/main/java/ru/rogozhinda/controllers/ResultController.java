package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.dto.result.ResultCreateForm;

// Контракт контроллера результатов
@RequestMapping("/results")
public interface ResultController {
    /**
     * Отображает детальную информацию о результате.
     */
    @GetMapping("/{id}")
    String details(
            @PathVariable String id,
            Model model
    );

    /**
     * Отображает форму создания результата.
     */
    @GetMapping("/create")
    String createForm(Model model);

    /**
     * Обрабатывает создание новой результата.
     */
    @PostMapping("/create")
    String create(
            @Valid ResultCreateForm resultCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Отображает форму редактирования результата.
     */
    @GetMapping("/{id}/edit")
    String editForm(
            @PathVariable String id,
            Model model
    );

    /**
     * Обрабатывает обновление существующей результата.
     */
    @PostMapping("/{id}/edit")
    String edit(
            @PathVariable String id,
            @Valid @ModelAttribute("resultCreateForm") ResultCreateForm resultCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Обрабатывает удаление результата.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}