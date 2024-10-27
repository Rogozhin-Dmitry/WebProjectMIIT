package ru.rogozhinda.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "drivers")
public class Driver extends BaseEntity {
    private String name;
    private String nationality;
    private Integer number;
    private Integer age;
    private Integer height;
    private Integer weight;
    private Integer points;
    private Date birthday;
    private Team team;
    private Set<RaceTeam> driverCars;

    public Driver(String name, String nationality, Integer number, Integer age, Integer height, Integer weight, Integer points, Date birthday, Team team, Set<RaceTeam> driverCars) {
        this.name = name;
        this.nationality = nationality;
        this.number = number;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.points = points;
        this.birthday = birthday;
        this.team = team;
        this.driverCars = driverCars;
    }

    public Driver() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name = "weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Column(name = "points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ManyToOne
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToMany(mappedBy = "driver")
    public Set<RaceTeam> getDriverCars() {
        return driverCars;
    }

    public void setDriverCars(Set<RaceTeam> driverCars) {
        this.driverCars = driverCars;
    }
}
