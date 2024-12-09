package ru.rogozhinda.dto.race;

import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;

import java.util.List;

public record RaceDetailsViewModel(
        BaseViewModel base,
        RaceViewModel race,
        String circuit,
        String broadcasters,
        List<RaceTeamViewModel> raceTeams
) {
}
