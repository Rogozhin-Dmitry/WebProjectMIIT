package ru.rogozhinda.dto.race;

import ru.rogozhinda.dto.base.BaseViewModel;

public class RaceDetailsViewModel {
    private BaseViewModel base;
    private RaceViewModel race;
    private String circuit;
    private String broadcasters;
//        List<RaceTeamViewModel> raceTeams


    public RaceDetailsViewModel(BaseViewModel base, RaceViewModel race, String circuit, String broadcasters) {
        this.base = base;
        this.race = race;
        this.circuit = circuit;
        this.broadcasters = broadcasters;
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
}
