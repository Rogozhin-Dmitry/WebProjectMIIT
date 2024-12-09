package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rogozhinda.dto.car.CarCreateForm;
import ru.rogozhinda.dto.car.CarEditForm;
import ru.rogozhinda.dto.car.CarsSearchForm;

// Контракт контроллера гоночных машин
@RequestMapping("/cars")
public interface CarController extends BaseController {
    /**
     * Отображает список гоночных машин с фильтрацией и пагинацией.
     */
    @GetMapping
    String listCars(
            @ModelAttribute("form") CarsSearchForm form,
            Model model
    );

    /**
     * Отображает детальную информацию о гоночной машине.
     */
    @GetMapping("/{id}")
    String details(
            @PathVariable String id,
            Model model
    );

    /**
     * Отображает форму создания гоночной машины.
     */
    @GetMapping("/create")
    String createForm(Model model);

    /**
     * Обрабатывает создание новой гоночной машины.
     */
    @PostMapping("/create")
    String create(
            @Valid @ModelAttribute("form") CarCreateForm form,
            BindingResult bindingResult,
            Model model
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
     * Обрабатывает обновление существующей гоночной машины.
     */
    @PostMapping("/{id}/edit")
    String edit(
            @PathVariable String id,
            @Valid @ModelAttribute("form") CarEditForm form,
            BindingResult bindingResult,
            Model model
    );

    /**
     * Обрабатывает удаление гоночной машины.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}