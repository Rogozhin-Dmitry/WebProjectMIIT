package ru.rogozhinda.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    private String name;
    private String country;
    private Integer trophies;
    private Integer points;
    private Date founded;
    private Set<Driver> drivers;
    private Set<Car> cars;
    private Set<RaceTeam> raceTeams;

    public Team(String name, String country, Integer trophies, Integer points, Date founded, Set<Driver> drivers, Set<Car> cars, Set<RaceTeam> raceTeams) {
        this.name = name;
        this.country = country;
        this.trophies = trophies;
        this.points = points;
        this.founded = founded;
        this.drivers = drivers;
        this.cars = cars;
        this.raceTeams = raceTeams;
    }

    public Team() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "trophies")
    public Integer getTrophies() {
        return trophies;
    }

    public void setTrophies(Integer trophies) {
        this.trophies = trophies;
    }

    @Column(name = "points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Column(name = "founded")
    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    @OneToMany(mappedBy = "team")
    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @OneToMany(mappedBy = "team")
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    public Set<RaceTeam> getRaceTeams() {
        return raceTeams;
    }

    public void setRaceTeams(Set<RaceTeam> raceTeams) {
        this.raceTeams = raceTeams;
    }
}
