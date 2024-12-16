package ru.rogozhinda.dto.driver;

public class DriverViewModel {
    private String id;
    private String name;
    private Integer age;
    private String nationality;
    private Integer number;
    private Integer points;

    public DriverViewModel(String id, String name, Integer age, String nationality, Integer number, Integer points) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.number = number;
        this.points = points;
    }

    public DriverViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
