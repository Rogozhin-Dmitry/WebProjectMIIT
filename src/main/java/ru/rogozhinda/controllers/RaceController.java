package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rogozhinda.dto.race.RaceCreateForm;
import ru.rogozhinda.dto.race.RaceEditForm;
import ru.rogozhinda.dto.race.RacesSearchForm;

// Контракт контроллера гонок
@RequestMapping("/races")
public interface RaceController extends BaseController {
    /**
     * Отображает список гонок с фильтрацией и пагинацией.
     */
    @GetMapping
    String listRaces(
            @ModelAttribute("form") RacesSearchForm form,
            Model model
    );

    /**
     * Отображает детальную информацию о гонке.
     */
    @GetMapping("/{id}")
    String details(
            @PathVariable String id,
            Model model
    );

    /**
     * Отображает форму создания гонки.
     */
    @GetMapping("/create")
    String createForm(Model model);

    /**
     * Обрабатывает создание новой гонки.
     */
    @PostMapping("/create")
    String create(
            @Valid @ModelAttribute("form") RaceCreateForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Отображает форму редактирования гонки.
     */
    @GetMapping("/{id}/edit")
    String editForm(
            @PathVariable String id,
            Model model
    );

    /**
     * Обрабатывает обновление существующей гонки.
     */
    @PostMapping("/{id}/edit")
    String edit(
            @PathVariable String id,
            @Valid @ModelAttribute("form") RaceEditForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Обрабатывает удаление гонки.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}