package ru.rogozhinda.dto.driver;

import jakarta.validation.constraints.Min;

public record DriversSearchForm(
String searchTerm,
@Min(value = 0, message = "Страница должна быть больше 0")
Integer page,
@Min(value = 1, message = "Размер страницы должен быть больше 0")
Integer size
) {
}
