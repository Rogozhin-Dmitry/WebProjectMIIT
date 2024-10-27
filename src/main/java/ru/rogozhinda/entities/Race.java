package ru.rogozhinda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {
    private String name;
    private String location;
    private Integer duration;
    private Date date;
    private String circuit;
    private String broadcasters;
    private Set<RaceTeam> raceTeams;

    public Race(String name, String location, Integer duration, Date date, String circuit, String broadcasters, Set<RaceTeam> raceTeams) {
        this.name = name;
        this.location = location;
        this.duration = duration;
        this.date = date;
        this.circuit = circuit;
        this.broadcasters = broadcasters;
        this.raceTeams = raceTeams;
    }

    public Race() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "circuit")
    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    @Column(name = "broadcasters")
    public String getBroadcasters() {
        return broadcasters;
    }

    public void setBroadcasters(String broadcasters) {
        this.broadcasters = broadcasters;
    }

    @OneToMany(mappedBy = "race")
    public Set<RaceTeam> getRaceTeams() {
        return raceTeams;
    }

    public void setRaceTeams(Set<RaceTeam> raceTeams) {
        this.raceTeams = raceTeams;
    }
}
