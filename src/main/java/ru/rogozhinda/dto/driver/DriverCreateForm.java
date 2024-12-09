package ru.rogozhinda.dto.driver;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DriverCreateForm(
        @NotBlank(message = "Имя обязательно") String name,
        @NotBlank(message = "Национальность обязательна") String nationality,
        @Max(value = 100, message = "Возраст должен быть меньше 100")
        @Min(value = 16, message = "Возраст должен быть больше 16")
        Integer age,
        @Min(value = 0, message = "Номер должен быть больше 0") Integer number,
        @Min(value = -1, message = "Количество очков должно быть не отрицательным") Integer points,
        @Min(value = 0, message = "Рост должен быть больше 0")
        @Max(value = 250, message = "Рост должен быть меньше 250")
        Integer height,
        @Min(value = 0, message = "Вес должен быть больше 0")
        @Max(value = 250, message = "Вес должен быть меньше 250")
        Integer weight,
        @NotBlank(message = "Дата рождения обязательна") Date birthday
) {
}
