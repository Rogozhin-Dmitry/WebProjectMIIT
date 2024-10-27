package ru.rogozhinda.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "races_teams")
public class RaceTeam extends BaseEntity {
    private Team team;
    private Race race;
    private Driver driver;
    private Car car;
    private Result result;

    public RaceTeam(Team team, Race race, Driver driver, Car car, Result result) {
        this.team = team;
        this.race = race;
        this.driver = driver;
        this.car = car;
        this.result = result;
    }

    public RaceTeam() {
    }

    @ManyToOne
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne
    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @ManyToOne
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @ManyToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @OneToOne
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
