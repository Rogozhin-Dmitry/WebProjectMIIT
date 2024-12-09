package ru.rogozhinda.dto.raceteam;

import jakarta.validation.constraints.NotBlank;

public record RaceTeamCreateForm(
        @NotBlank(message = "Команда обязательна") Integer teamId,
        @NotBlank(message = "Пилот обязателен") Integer driverId,
        @NotBlank(message = "Машина обязательна") Integer carId,
        @NotBlank(message = "Результат обязателен") Integer resultId
) {
}
