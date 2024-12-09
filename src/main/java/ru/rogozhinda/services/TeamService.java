package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.team.TeamDetailsViewModel;
import ru.rogozhinda.dto.team.TeamViewModel;
import ru.rogozhinda.entities.Team;


import java.util.List;

public interface TeamService {
    Page<TeamViewModel> getTeams(Pageable pageable);

    TeamDetailsViewModel getTeam(Integer id);

    TeamDetailsViewModel createTeam(Team team);

    void deleteTeam(Integer id);

    void saveAllTeams(List<Team> books);
}
