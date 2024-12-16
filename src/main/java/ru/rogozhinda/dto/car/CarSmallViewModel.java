package ru.rogozhinda.dto.car;

public class CarSmallViewModel {
    public String id;
    public String model;
    public Integer year;

    public CarSmallViewModel(String id, String model, Integer year) {
        this.id = id;
        this.model = model;
        this.year = year;
    }

    public CarSmallViewModel() {
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
}
