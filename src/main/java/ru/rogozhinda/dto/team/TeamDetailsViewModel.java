package ru.rogozhinda.dto.team;

import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.car.CarSmallViewModel;
import ru.rogozhinda.dto.driver.DriverSmallViewModel;

import java.util.List;

public class TeamDetailsViewModel {
    private BaseViewModel base;
    private TeamViewModel team;
    private List<DriverSmallViewModel> drivers;
    private List<CarSmallViewModel> cars;


    public TeamDetailsViewModel(BaseViewModel base, TeamViewModel team, List<DriverSmallViewModel> drivers, List<CarSmallViewModel> cars) {
        this.base = base;
        this.team = team;
        this.drivers = drivers;
        this.cars = cars;
    }

    public TeamDetailsViewModel() {
    }

    public BaseViewModel getBase() {
        return base;
    }

    public void setBase(BaseViewModel base) {
        this.base = base;
    }

    public TeamViewModel getTeam() {
        return team;
    }

    public void setTeam(TeamViewModel team) {
        this.team = team;
    }

    public List<DriverSmallViewModel> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverSmallViewModel> drivers) {
        this.drivers = drivers;
    }

    public List<CarSmallViewModel> getCars() {
        return cars;
    }

    public void setCars(List<CarSmallViewModel> cars) {
        this.cars = cars;
    }
}
