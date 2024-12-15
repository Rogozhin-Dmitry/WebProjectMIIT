package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import ru.rogozhinda.dto.raceteam.RaceTeamDetailsViewModel;
//import ru.rogozhinda.dto.raceteam.RaceTeamEditForm;
import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;
import ru.rogozhinda.entities.RaceTeam;

import java.util.List;

public interface RaceTeamService {
    Page<RaceTeamViewModel> getRaceTeams(Pageable pageable);

//    RaceTeamDetailsViewModel getRaceTeam(Integer id);
//
//    RaceTeamDetailsViewModel createRaceTeam(RaceTeamEditForm raceteamEditForm);

    void deleteRaceTeam(Integer id);

    void saveAllRaceTeams(List<RaceTeam> books);
}
