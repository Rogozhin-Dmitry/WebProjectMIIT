package ru.rogozhinda.dto.driver;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DriverCreateForm {
    @NotBlank(message = "Имя обязательно")
    private String name;

    @NotBlank(message = "Национальность обязательна")
    private String nationality;

    @Max(value = 100, message = "Возраст должен быть меньше 100")
    @Min(value = 16, message = "Возраст должен быть больше 16")
    private Integer age;

    @Min(value = 0, message = "Номер должен быть больше 0")
    private Integer number;

    @Min(value = -1, message = "Количество очков должно быть не отрицательным")
    private Integer points;

    @Min(value = 0, message = "Рост должен быть больше 0")
    @Max(value = 250, message = "Рост должен быть меньше 250")
    private Integer height;

    @Min(value = 0, message = "Вес должен быть больше 0")
    @Max(value = 250, message = "Вес должен быть меньше 250")
    private Integer weight;

    @NotNull(message = "Дата рождения обязательна")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;

    public DriverCreateForm(String name, String nationality, Integer age, Integer number, Integer points, Integer height, Integer weight, Date birthday) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.number = number;
        this.points = points;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
    }

    public DriverCreateForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
