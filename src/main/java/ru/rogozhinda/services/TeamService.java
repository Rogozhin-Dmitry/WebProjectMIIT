package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.driver.DriverSmallViewModel;
import ru.rogozhinda.dto.team.TeamCreateForm;
import ru.rogozhinda.dto.team.TeamDetailsViewModel;
import ru.rogozhinda.dto.team.TeamViewModel;
import ru.rogozhinda.dto.team.TeamsSearchForm;
import ru.rogozhinda.entities.Team;

import java.util.List;

public interface TeamService {
    Page<TeamViewModel> getTeams(Pageable pageable);

    Page<TeamViewModel> getTeamsByFilter(Pageable pageable, TeamsSearchForm form);

    long countTeams();

    TeamDetailsViewModel getTeam(String id);

    TeamCreateForm getEditTeam(String id);

    void editTeam(String id, TeamCreateForm teamCreateForm);

    void createTeam(TeamCreateForm teamCreateForm, List<String> teamDriversIds, List<String> teamCarsIds);

    void deleteTeam(String id);

    void saveAllTeams(List<Team> teams);
}
