package ru.rogozhinda.dto.car;

public class CarViewModel {
    public String id;
    public String model;
    public Integer year;
    public Integer horsepower;

    public CarViewModel(String id, String model, Integer year, Integer horsepower) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.horsepower = horsepower;
    }

    public CarViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }
}
