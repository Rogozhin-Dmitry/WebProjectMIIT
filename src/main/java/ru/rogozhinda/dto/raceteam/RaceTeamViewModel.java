package ru.rogozhinda.dto.raceteam;

import ru.rogozhinda.dto.car.CarViewModel;
import ru.rogozhinda.dto.driver.DriverViewModel;
import ru.rogozhinda.dto.result.ResultViewModel;
import ru.rogozhinda.dto.team.TeamViewModel;

public class RaceTeamViewModel {
    private String id;
    private TeamViewModel team;
    private DriverViewModel driver;
    private CarViewModel car;
    private ResultViewModel result;


    public RaceTeamViewModel(String id, TeamViewModel team, DriverViewModel driver, CarViewModel car, ResultViewModel result) {
        this.id = id;
        this.team = team;
        this.driver = driver;
        this.car = car;
        this.result = result;
    }

    public RaceTeamViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeamViewModel getTeam() {
        return team;
    }

    public void setTeam(TeamViewModel team) {
        this.team = team;
    }

    public DriverViewModel getDriver() {
        return driver;
    }

    public void setDriver(DriverViewModel driver) {
        this.driver = driver;
    }

    public CarViewModel getCar() {
        return car;
    }

    public void setCar(CarViewModel car) {
        this.car = car;
    }

    public ResultViewModel getResult() {
        return result;
    }

    public void setResult(ResultViewModel result) {
        this.result = result;
    }
}
