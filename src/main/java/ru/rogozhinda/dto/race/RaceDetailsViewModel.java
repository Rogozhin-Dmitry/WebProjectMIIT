package ru.rogozhinda.dto.race;

import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;

import java.util.List;

public class RaceDetailsViewModel {
    private BaseViewModel base;
    private RaceViewModel race;
    private String circuit;
    private String broadcasters;
    private List<RaceTeamViewModel> raceTeams;


    public RaceDetailsViewModel(BaseViewModel base, RaceViewModel race, String circuit, String broadcasters, List<RaceTeamViewModel> raceTeams) {
        this.base = base;
        this.race = race;
        this.circuit = circuit;
        this.broadcasters = broadcasters;
        this.raceTeams = raceTeams;
    }

    public RaceDetailsViewModel() {
    }

    public BaseViewModel getBase() {
        return base;
    }

    public void setBase(BaseViewModel base) {
        this.base = base;
    }

    public RaceViewModel getRace() {
        return race;
    }

    public void setRace(RaceViewModel race) {
        this.race = race;
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

    public List<RaceTeamViewModel> getRaceTeams() {
        return raceTeams;
    }

    public void setRaceTeams(List<RaceTeamViewModel> raceTeams) {
        this.raceTeams = raceTeams;
    }
}
