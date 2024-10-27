package ru.rogozhinda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "result")
public class Result extends BaseEntity {
    private Integer position;
    private Integer laps;
    private Integer time;
    private Integer lapTime;
    private Integer gap;
    private Integer points;
    private String status;
    private RaceTeam raceTeam;

    public Result(Integer position, Integer laps, Integer time, Integer lapTime, Integer gap, Integer points, String status, RaceTeam raceTeam) {
        this.position = position;
        this.laps = laps;
        this.time = time;
        this.lapTime = lapTime;
        this.gap = gap;
        this.points = points;
        this.status = status;
        this.raceTeam = raceTeam;
    }

    public Result() {
    }

    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Column(name = "laps")
    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @Column(name = "time")
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Column(name = "lapTime")
    public Integer getLapTime() {
        return lapTime;
    }

    public void setLapTime(Integer lapTime) {
        this.lapTime = lapTime;
    }

    @Column(name = "gap")
    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    @Column(name = "points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToOne(mappedBy = "result")
    public RaceTeam getRaceTeam() {
        return raceTeam;
    }

    public void setRaceTeam(RaceTeam raceTeam) {
        this.raceTeam = raceTeam;
    }
}
