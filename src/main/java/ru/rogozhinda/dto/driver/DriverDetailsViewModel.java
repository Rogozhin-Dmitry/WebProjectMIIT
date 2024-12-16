package ru.rogozhinda.dto.driver;

import ru.rogozhinda.dto.base.BaseViewModel;

import java.util.Date;

public class DriverDetailsViewModel {
    private BaseViewModel base;
    private DriverViewModel driver;
    private Integer height;
    private Integer weight;
    private Date birthday;

    public DriverDetailsViewModel(BaseViewModel base, DriverViewModel driver, Integer height, Integer weight, Date birthday) {
        this.base = base;
        this.driver = driver;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
    }

    public DriverDetailsViewModel() {
    }

    public BaseViewModel getBase() {
        return base;
    }

    public void setBase(BaseViewModel base) {
        this.base = base;
    }

    public DriverViewModel getDriver() {
        return driver;
    }

    public void setDriver(DriverViewModel driver) {
        this.driver = driver;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
