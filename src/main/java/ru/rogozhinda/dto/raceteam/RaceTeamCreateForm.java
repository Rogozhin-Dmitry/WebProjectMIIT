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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
