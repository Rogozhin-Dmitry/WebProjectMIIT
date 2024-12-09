package ru.rogozhinda.dto.team;

import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.car.CarListViewModel;
import ru.rogozhinda.dto.driver.DriverListViewModel;

public record TeamDetailsViewModel(
        BaseViewModel base,
        TeamViewModel team,
        CarListViewModel cars,
        DriverListViewModel drivers
) {
}
