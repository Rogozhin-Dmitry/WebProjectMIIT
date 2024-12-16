package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.dto.raceteam.RaceTeamCreateForm;

// Контракт контроллера промежуточной таблицы
@RequestMapping("/raceteams")
public interface RaceTeamController {
    /**
     * Отображает форму создания промежуточной таблицы.
     */
    @GetMapping("/create")
    String createForm(Model model, @RequestParam("raceId") String raceId);

    /**
     * Обрабатывает создание новой промежуточной таблицы.
     */
    @PostMapping("/create")
    String create(
            @Valid RaceTeamCreateForm raceteamCreateForm,
            BindingResult bindingResult,
            Model model,
            @RequestParam("raceId") String raceId,
            RedirectAttributes redirectAttributes
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
            @Valid @ModelAttribute("raceteamCreateForm") RaceTeamCreateForm raceteamCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Обрабатывает удаление промежуточной таблицы.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}