package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rogozhinda.dto.team.TeamCreateForm;
import ru.rogozhinda.dto.team.TeamEditForm;
import ru.rogozhinda.dto.team.TeamsSearchForm;

// Контракт контроллера команд
@RequestMapping("/teams")
public interface TeamController extends BaseController {
    /**
     * Отображает список команд с фильтрацией и пагинацией.
     */
    @GetMapping
    String listTeams(
            @ModelAttribute("form") TeamsSearchForm form,
            Model model
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
            @Valid @ModelAttribute("form") TeamCreateForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Отображает форму редактирования команды.
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
            @Valid @ModelAttribute("form") TeamEditForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Обрабатывает удаление команды.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}