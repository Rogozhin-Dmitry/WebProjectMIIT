package ru.rogozhinda.dto.team;

import java.util.Date;

public class TeamViewModel {
    private String id;
    private Integer points;
    private String name;
    private String country;
    private Integer trophies;
    private Date founded;

    public TeamViewModel(String id, Integer points, String name, String country, Integer trophies, Date founded) {
        this.id = id;
        this.points = points;
        this.name = name;
        this.country = country;
        this.trophies = trophies;
        this.founded = founded;
    }

    public TeamViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }
}
