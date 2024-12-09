package ru.rogozhinda.dto.race;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public record RaceEditForm(
        @NotBlank(message = "Название обязательно") String name,
        @NotBlank(message = "Место проведения обязательно") String location,
        @Min(value = -1, message = "Длительность гонки должна быть не отрицательной") Integer duration,
        @NotBlank(message = "Дата проведения обязательна") Date date,
        String circuit,
        String broadcasters,
        List<Integer> raceTeamsIds
) {
}