package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.dto.team.TeamCreateForm;
import ru.rogozhinda.dto.team.TeamsSearchForm;

// Контракт контроллера команд
@RequestMapping("/teams")
public interface TeamController {
    /**
     * Отображает список команд с фильтрацией и пагинацией.
     */
    @GetMapping
    String listTeams(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            Model model
    );

    /**
     * Получает запрос для фильтрации.
     */
    @PostMapping
    String listTeamsSearch(
            @ModelAttribute("teamsSearchForm") TeamsSearchForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Отображает детальную информацию о команде.
     */
    @GetMapping("/{id}")
    String details(
            @PathVariable String id,
            Model model
    );

    /**
     * Отображает форму создания команды.
     */
    @GetMapping("/create")
    String createForm(Model model);

    /**
     * Обрабатывает создание новой команды.
     */
    @PostMapping("/create")
    String create(
            @Valid TeamCreateForm teamCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Отображает форму редактирования гоночной машины.
     */
    @GetMapping("/{id}/edit")
    String editForm(
            @PathVariable String id,
            Model model
    );

    /**
     * Обрабатывает обновление существующей команды.
     */
    @PostMapping("/{id}/edit")
    String edit(
            @PathVariable String id,
            @Valid @ModelAttribute("teamCreateForm") TeamCreateForm teamCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Обрабатывает удаление команды.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}