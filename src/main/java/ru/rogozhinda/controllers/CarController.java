package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.dto.car.CarCreateForm;
import ru.rogozhinda.dto.car.CarsSearchForm;

// Контракт контроллера гоночных машин
@RequestMapping("/cars")
@EnableCaching
public interface CarController {
    /**
     * Отображает список гоночных машин с фильтрацией и пагинацией.
     */
    @Cacheable("races")
    @GetMapping
    String listCars(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            Model model
    );

    /**
     * Получает запрос для фильтрации.
     */
    @PostMapping
    String listCarsSearch(
            @ModelAttribute("carsSearchForm") CarsSearchForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
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
            @Valid CarCreateForm carCreateForm,
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
     * Обрабатывает обновление существующей гоночной машины.
     */
    @PostMapping("/{id}/edit")
    String edit(
            @PathVariable String id,
            @Valid @ModelAttribute("carCreateForm") CarCreateForm carCreateForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    );

    /**
     * Обрабатывает удаление гоночной машины.
     */
    @GetMapping("/{id}/delete")
    String delete(@PathVariable String id);
}