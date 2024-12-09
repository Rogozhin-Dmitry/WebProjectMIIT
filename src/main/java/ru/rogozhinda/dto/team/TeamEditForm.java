package ru.rogozhinda.dto.team;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record TeamEditForm(
        @NotBlank(message = "Название обязательно") String name,
        @NotBlank(message = "Страна основания обязательна") String country,
        @Min(value = -1, message = "Количество трофеев должно быть не отрицательным") Integer trophies,
        @Min(value = -1, message = "Количество очков должно быть не отрицательным") Integer points,
        @NotBlank(message = "Дата основания обязательна") Date founded
) {
}