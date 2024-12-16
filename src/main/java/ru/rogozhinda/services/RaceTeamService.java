package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.raceteam.RaceTeamCreateForm;
import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;
import ru.rogozhinda.entities.RaceTeam;

import java.util.List;

public interface RaceTeamService {
    Page<RaceTeamViewModel> getRaceTeams(Pageable pageable);

    long countRaceTeams();

    RaceTeamCreateForm getEditRaceTeam(String id);

    void editRaceTeam(String id, RaceTeamCreateForm raceteamCreateForm);

    void createRaceTeam(RaceTeamCreateForm raceteamCreateForm);

    void deleteRaceTeam(String id);

    void saveAllRaceTeams(List<RaceTeam> raceteams);
}
