package ru.rogozhinda.dto.car;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CarCreateForm(
        @NotBlank(message = "Название обязательно") String model,
        @NotBlank(message = "Модель двигателя обязательна") String engine,
        @Min(value = 1900, message = "Год должен быть больше 1900") Integer year,
        @Min(value = 600, message = "Вес должен быть больше 600 кг") Integer weight,
        @Min(value = 50, message = "Количество лошадиных сил должно быть больше 50") Integer horsepower
) {
}
