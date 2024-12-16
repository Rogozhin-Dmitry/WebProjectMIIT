package ru.rogozhinda.dto.result;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ResultCreateForm {
    @NotNull(message = "Позиция обязательна")
    @Min(value = -1, message = "Позиция должна быть не отрицательным")
    private Integer position;

    @NotNull(message = "Время финиша обязательно")
    @Min(value = -1, message = "Время финиша должно быть не отрицательным")
    private Integer time;

    @Min(value = -1, message = "Количество кругов должно быть не отрицательным")
    private Integer laps;

    @Min(value = -1, message = "Лучшее время круга должно быть не отрицательным")
    private Integer lapTime;

    @Min(value = -1, message = "Отставание от лидера должно быть не отрицательным")
    private Integer gap;
    private Integer points;
    private String status;

    public ResultCreateForm(Integer position, Integer time, Integer laps, Integer lapTime, Integer gap, Integer points, String status) {
        this.position = position;
        this.time = time;
        this.laps = laps;
        this.lapTime = lapTime;
        this.gap = gap;
        this.points = points;
        this.status = status;
    }

    public ResultCreateForm() {
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public Integer getLapTime() {
        return lapTime;
    }

    public void setLapTime(Integer lapTime) {
        this.lapTime = lapTime;
    }

    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
