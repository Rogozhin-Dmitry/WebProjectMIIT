package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rogozhinda.dto.raceteam.RaceTeamCreateForm;
import ru.rogozhinda.dto.raceteam.RaceTeamEditForm;

// Контракт контроллера промежуточной таблицы
@RequestMapping("/raceteams")
public interface RaceTeamController extends BaseController {
    /**
     * Отображает форму создания промежуточной таблицы.
     */
    @GetMapping("/create")
    String createForm(Model model);

    /**
     * Обрабатывает создание новой промежуточной таблицы.
     */
    @PostMapping("/create")
    String create(
            @Valid @ModelAttribute("form") RaceTeamCreateForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Отображает форму редактирования промежуточной таблицы.
     */
    @GetMapping("/{id}/edit")
    String editForm(
            @PathVariable String id,
            Model model
    );

    /**
     * Обрабатывает обновление существующей промежуточной таблицы.
     */
    @PostMapping("/{id}/edit")
    String edit(
            @PathVariable String id,
            @Valid @ModelAttribute("form") RaceTeamEditForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Обрабатывает удаление промежуточной таблицы.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}