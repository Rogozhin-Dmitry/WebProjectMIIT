package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.dto.race.RaceCreateForm;
import ru.rogozhinda.dto.race.RacesSearchForm;

// Контракт контроллера гонок
@RequestMapping("/races")
public interface RaceController {
    /**
     * Отображает список гонок с фильтрацией и пагинацией.
     */
    @GetMapping
    String listRaces(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            Model model
    );

    /**
     * Получает запрос для фильтрации.
     */
    @PostMapping
    String listRacesSearch(
            @ModelAttribute("racesSearchForm") RacesSearchForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
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
            @Valid RaceCreateForm raceCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
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
            @Valid @ModelAttribute("raceCreateForm") RaceCreateForm raceCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Обрабатывает удаление гонки.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}