package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.dto.driver.DriverCreateForm;
import ru.rogozhinda.dto.driver.DriversSearchForm;

// Контракт контроллера водителей
@RequestMapping("/drivers")
public interface DriverController {
    /**
     * Отображает список водителей с фильтрацией и пагинацией.
     */
    @GetMapping
    String listDrivers(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            Model model
    );

    /**
     * Получает запрос для фильтрации.
     */
    @PostMapping
    String listDriversSearch(
            @ModelAttribute("driversSearchForm") DriversSearchForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Отображает детальную информацию о водителе.
     */
    @GetMapping("/{id}")
    String details(
            @PathVariable String id,
            Model model
    );

    /**
     * Отображает форму создания водителя.
     */
    @GetMapping("/create")
    String createForm(Model model);

    /**
     * Обрабатывает создание нового водителя.
     */
    @PostMapping("/create")
    String create(
            @Valid DriverCreateForm driverCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Отображает форму редактирования водителя.
     */
    @GetMapping("/{id}/edit")
    String editForm(
            @PathVariable String id,
            Model model
    );

    /**
     * Обрабатывает обновление существующего водителя.
     */
    @PostMapping("/{id}/edit")
    String edit(
            @PathVariable String id,
            @Valid @ModelAttribute("driverCreateForm") DriverCreateForm driverCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Обрабатывает удаление водителя.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}