package ru.rogozhinda.dto.result;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ResultCreateForm(
        @NotBlank(message = "Позиция обязательна")
        @Min(value = -1, message = "Позиция должна быть не отрицательным")
        Integer position,
        @NotBlank(message = "Время гонки обязательно")
        @Min(value = -1, message = "Время гонки должно быть не отрицательным")
        Integer time,
        @Min(value = -1, message = "Количество кругов должно быть не отрицательным") Integer laps,
        @Min(value = -1, message = "Лучшее время круга должно быть не отрицательным") Integer lapTime,
        @Min(value = -1, message = "Отставание от лидера должно быть не отрицательным") Integer gap,
        Integer points,
        String status
) {
}
