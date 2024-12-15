package ru.rogozhinda.dto.car;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CarCreateForm {
    @NotBlank(message = "Модель обязательна")
    private String model;

    @NotBlank(message = "Модель двигателя обязательна")
    private String engine;

    @NotNull(message = "Год обязателен")
    @Min(value = 1900, message = "Год должен быть больше 1900")
    @Max(value = 2025, message = "Год должен быть меньше 2025")
    private Integer year;

    @NotNull(message = "Вес обязателен")
    @Min(value = 600, message = "Вес должен быть больше 600 кг")
    @Max(value = 9999, message = "Вес должен быть меньше 9999 кг")
    private Integer weight;

    @NotNull(message = "Количество лошадиных сил обязательно")
    @Min(value = 50, message = "Количество лошадиных сил должно быть больше 50")
    @Max(value = 9999, message = "Количество лошадиных сил должно быть меньше 9999")
    private Integer horsepower;

    public CarCreateForm(String model, String engine, Integer year, Integer weight, Integer horsepower) {
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.weight = weight;
        this.horsepower = horsepower;
    }

    public CarCreateForm() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }
}
