package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.team.TeamDetailsViewModel;
import ru.rogozhinda.dto.team.TeamEditForm;
import ru.rogozhinda.dto.team.TeamViewModel;
import ru.rogozhinda.entities.Team;
import ru.rogozhinda.repositories.TeamRepository;
import ru.rogozhinda.services.TeamService;

import java.util.List;

public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper mapper;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper mapper) {
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<TeamViewModel> getTeams(Pageable pageable) {
        return teamRepository.findAll(pageable).map(team -> mapper.map(team, TeamViewModel.class));
    }

    @Override
    public TeamDetailsViewModel getTeam(Integer id) {
        return mapper.map(teamRepository.findById(id), TeamDetailsViewModel.class);
    }

    @Override
    public TeamDetailsViewModel createTeam(TeamEditForm teamEditForm) {
        Team team = mapper.map(teamEditForm, Team.class);
        team = teamRepository.save(team);
        return mapper.map(team, TeamDetailsViewModel.class);
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void saveAllTeams(List<Team> books) {

    }
}
