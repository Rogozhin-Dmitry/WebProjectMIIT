package ru.rogozhinda.services;

import ru.rogozhinda.dto.raceteam.RaceTeamCreateForm;
import ru.rogozhinda.entities.RaceTeam;

import java.util.List;

public interface RaceTeamService {
    RaceTeamCreateForm getEditRaceTeam(String id);

    void editRaceTeam(String id, RaceTeamCreateForm raceteamCreateForm);

    void createRaceTeam(RaceTeamCreateForm raceteamCreateForm, String raceId);

    void deleteRaceTeam(String id);

    void saveAllRaceTeams(List<RaceTeam> raceteams);
}
