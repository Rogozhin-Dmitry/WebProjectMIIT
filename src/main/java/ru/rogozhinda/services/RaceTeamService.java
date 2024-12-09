package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;
import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;
import ru.rogozhinda.entities.RaceTeam;


import java.util.List;

public interface RaceTeamService {
    Page<RaceTeamViewModel> getRaceTeams(Pageable pageable);

    RaceTeamViewModel getRaceTeam(Integer id);

    RaceTeamViewModel createRaceTeam(RaceTeam raceteam);

    void deleteRaceTeam(Integer id);

    void saveAllRaceTeams(List<RaceTeam> books);
}
