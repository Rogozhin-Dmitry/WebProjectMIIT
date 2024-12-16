package ru.rogozhinda.dto.raceteam;

import jakarta.validation.constraints.NotBlank;

public class RaceTeamCreateForm {
    @NotBlank(message = "Нужно обязательно выбрать команду")
    private String teamId;

    @NotBlank(message = "Нужно обязательно выбрать пилота")
    private String driverId;

    @NotBlank(message = "Нужно обязательно выбрать машину")
    private String carId;

    public RaceTeamCreateForm(String teamId, String driverId, String carId, String resultId) {
        this.teamId = teamId;
        this.driverId = driverId;
        this.carId = carId;
    }

    public RaceTeamCreateForm() {
    }

    public @NotBlank(message = "Нужно обязательно выбрать команду") String getTeamId() {
        return teamId;
    }

    public void setTeamId(@NotBlank(message = "Нужно обязательно выбрать команду") String teamId) {
        this.teamId = teamId;
    }

    public @NotBlank(message = "Нужно обязательно выбрать пилота") String getDriverId() {
        return driverId;
    }

    public void setDriverId(@NotBlank(message = "Нужно обязательно выбрать пилота") String driverId) {
        this.driverId = driverId;
    }

    public @NotBlank(message = "Нужно обязательно выбрать машину") String getCarId() {
        return carId;
    }

    public void setCarId(@NotBlank(message = "Нужно обязательно выбрать машину") String carId) {
        this.carId = carId;
    }
}
