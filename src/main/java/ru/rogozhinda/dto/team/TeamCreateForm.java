package ru.rogozhinda.dto.team;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class TeamCreateForm {
    @NotBlank(message = "Название обязательно")
    private String name;

    @NotBlank(message = "Страна основания обязательна")
    private String country;

    @Min(value = -1, message = "Количество трофеев должно быть не отрицательным")
    private Integer trophies;

    @Min(value = -1, message = "Количество очков должно быть не отрицательным")
    private Integer points;

    @NotNull(message = "Дата основания обязательна")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date founded;

    private List<Boolean> driverIds;
    private List<Boolean> carIds;


    public TeamCreateForm(String name, String country, Integer trophies, Integer points, Date founded, List<Boolean> driverIds, List<Boolean> carIds) {
        this.name = name;
        this.country = country;
        this.trophies = trophies;
        this.points = points;
        this.founded = founded;
        this.driverIds = driverIds;
        this.carIds = carIds;
    }

    public TeamCreateForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTrophies() {
        return trophies;
    }

    public void setTrophies(Integer trophies) {
        this.trophies = trophies;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    public List<Boolean> getDriverIds() {
        return driverIds;
    }

    public void setDriverIds(List<Boolean> driverIds) {
        this.driverIds = driverIds;
    }

    public List<Boolean> getCarIds() {
        return carIds;
    }

    public void setCarIds(List<Boolean> carIds) {
        this.carIds = carIds;
    }
}
