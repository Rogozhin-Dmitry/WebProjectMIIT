package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rogozhinda.dto.driver.DriverCreateForm;
import ru.rogozhinda.dto.driver.DriverEditForm;
import ru.rogozhinda.dto.driver.DriversSearchForm;

// Контракт контроллера водителей
@RequestMapping("/drivers")
public interface DriverController extends BaseController {
    /**
     * Отображает список водителей с фильтрацией и пагинацией.
     */
    @GetMapping
    String listDrivers(
            @ModelAttribute("form") DriversSearchForm form,
            Model model
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
            @Valid @ModelAttribute("form") DriverCreateForm form,
            BindingResult bindingResult,
            Model model
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
            @Valid @ModelAttribute("form") DriverEditForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Обрабатывает удаление водителя.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}