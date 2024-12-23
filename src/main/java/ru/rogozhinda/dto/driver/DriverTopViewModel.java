package ru.rogozhinda.dto.driver;

public class DriverTopViewModel {
    private String id;
    private String name;
    private Integer age;
    private String nationality;
    private Long points;

    public DriverTopViewModel(String id, String name, Integer age, String nationality, Long points) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.points = points;
    }

    public DriverTopViewModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public DriverTopViewModel() {
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

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
