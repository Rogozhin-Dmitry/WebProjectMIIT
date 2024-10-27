package ru.rogozhinda.entities;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    private String model;
    private String engine;
    private Integer year;
    private Integer weight;
    private Integer horsepower;
    private Team team;
    private Set<RaceTeam> driverCars;

    public Car(String model, String engine, Integer year, Integer weight, Integer horsepower, Team team, Set<RaceTeam> driverCars) {
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.weight = weight;
        this.horsepower = horsepower;
        this.team = team;
        this.driverCars = driverCars;
    }

    public Car() {
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "engine")
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Column(name = "horsepower")
    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    @ManyToOne
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToMany(mappedBy = "car")
    public Set<RaceTeam> getDriverCars() {
        return driverCars;
    }

    public void setDriverCars(Set<RaceTeam> driverCars) {
        this.driverCars = driverCars;
    }
}
