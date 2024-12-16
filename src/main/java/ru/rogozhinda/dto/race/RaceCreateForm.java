package ru.rogozhinda.dto.race;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RaceCreateForm {
    @NotBlank(message = "Название обязательно")
    private String name;
    @NotBlank(message = "Место проведения обязательно")
    private String location;
    @NotNull(message = "Длительность гонки обязательна")
    @Min(value = -1, message = "Длительность гонки должна быть не отрицательной")
    private Integer duration;
    @NotNull(message = "Дата проведения обязательна")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private String circuit;
    private String broadcasters;
//    List<Integer> raceTeamsIds;


    public RaceCreateForm(String name, String location, Integer duration, Date date, String circuit, String broadcasters) {
        this.name = name;
        this.location = location;
        this.duration = duration;
        this.date = date;
        this.circuit = circuit;
        this.broadcasters = broadcasters;
    }

    public RaceCreateForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getBroadcasters() {
        return broadcasters;
    }

    public void setBroadcasters(String broadcasters) {
        this.broadcasters = broadcasters;
    }
}
