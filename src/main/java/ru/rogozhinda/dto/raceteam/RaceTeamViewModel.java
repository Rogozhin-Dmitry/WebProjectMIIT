package ru.rogozhinda.dto.raceteam;

import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.car.CarViewModel;
import ru.rogozhinda.dto.driver.DriverViewModel;
import ru.rogozhinda.dto.result.ResultViewModel;
import ru.rogozhinda.dto.team.TeamViewModel;

public record RaceTeamViewModel(
        BaseViewModel baseViewModel,
        TeamViewModel team,
        DriverViewModel driver,
        CarViewModel car,
        ResultViewModel result
) {
}
