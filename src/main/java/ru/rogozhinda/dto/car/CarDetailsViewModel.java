package ru.rogozhinda.dto.car;

import ru.rogozhinda.dto.base.BaseViewModel;

public class CarDetailsViewModel {
    private BaseViewModel base; // TODO посмотреть на говно
    private CarViewModel car;
    private String engine;
    private Integer weight;

    public CarDetailsViewModel(BaseViewModel base, CarViewModel car, String engine, Integer weight) {
        this.base = base;
        this.car = car;
        this.engine = engine;
        this.weight = weight;
    }

    public CarDetailsViewModel() {
    }

    public BaseViewModel getBase() {
        return base;
    }

    public void setBase(BaseViewModel base) {
        this.base = base;
    }

    public CarViewModel getCar() {
        return car;
    }

    public void setCar(CarViewModel car) {
        this.car = car;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
