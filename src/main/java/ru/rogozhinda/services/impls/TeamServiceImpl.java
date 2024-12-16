package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.car.CarSmallViewModel;
import ru.rogozhinda.dto.driver.DriverSmallViewModel;
import ru.rogozhinda.dto.team.*;
import ru.rogozhinda.entities.Team;
import ru.rogozhinda.repositories.TeamRepository;
import ru.rogozhinda.services.CarService;
import ru.rogozhinda.services.DriverService;
import ru.rogozhinda.services.TeamService;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final DriverService driverService;
    private final CarService carService;
    private final ModelMapper mapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, DriverService driverService, CarService carService) {
        this.teamRepository = teamRepository;
        this.driverService = driverService;
        this.carService = carService;
        this.mapper = new ModelMapper();
    }

    @Override
    public Page<TeamViewModel> getTeams(Pageable pageable) {
        return teamRepository.findAll(pageable).map(team -> mapper.map(team, TeamViewModel.class));
    }

    @Override
    public Page<TeamViewModel> getTeamsByFilter(Pageable pageable, TeamsSearchForm form) {
        return teamRepository.findByFilter(pageable, form.getSearchTerm()).map(team -> mapper.map(team, TeamViewModel.class));
    }

    @Override
    public List<TeamSmallViewModel> getTeamsSmallAll() {
        return teamRepository.findAll().stream().map(team -> mapper.map(team, TeamSmallViewModel.class)).toList();
    }

    @Override
    public long countTeams() {
        return teamRepository.count();
    }

    @Override
    public TeamDetailsViewModel getTeam(String id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            return mapTeamDetail(team);
        } else {
            return null;
        }
    }

    @Override
    public TeamCreateForm getEditTeam(String id) {
        return mapper.map(teamRepository.findById(id), TeamCreateForm.class);
    }

    @Override
    public void editTeam(String id, TeamCreateForm teamCreateForm) {
        Team team = mapper.map(teamCreateForm, Team.class);
        team.setId(id);
        teamRepository.save(team);
    }

    @Override
    public void createTeam(TeamCreateForm teamCreateForm, List<String> teamDriversIds, List<String> teamCarsIds) {
        Team team = mapper.map(teamCreateForm, Team.class);
        teamRepository.save(team);
        driverService.setDrivers(team, teamDriversIds);
        carService.setCars(team, teamCarsIds);
    }

    @Override
    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void saveAllTeams(List<Team> teams) {
        teamRepository.saveAll(teams);
    }

    private TeamDetailsViewModel mapTeamDetail(Team team) {
        BaseViewModel title = new BaseViewModel(team.getName());
        TeamViewModel model = mapper.map(team, TeamViewModel.class);
        List<DriverSmallViewModel> drivers = team.getDrivers().stream().map(driver -> mapper.map(driver, DriverSmallViewModel.class)).toList();
        List<CarSmallViewModel> cars = team.getCars().stream().map(car -> mapper.map(car, CarSmallViewModel.class)).toList();

        return new TeamDetailsViewModel(title, model, drivers, cars);
    }
}
