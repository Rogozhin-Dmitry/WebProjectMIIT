package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.raceteam.RaceTeamCreateForm;
import ru.rogozhinda.entities.Race;
import ru.rogozhinda.entities.RaceTeam;
import ru.rogozhinda.repositories.*;
import ru.rogozhinda.services.*;

import java.util.List;

@Service
public class RaceTeamServiceImpl implements RaceTeamService {
    private final RaceTeamRepository raceteamRepository;
    private final ModelMapper mapper;

    private final TeamRepository teamRepository;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final RaceRepository raceRepository;


    @Autowired
    public RaceTeamServiceImpl(RaceTeamRepository raceteamRepository, TeamRepository teamRepository, DriverRepository driverRepository, CarRepository carRepository, RaceRepository raceRepository) {
        this.raceteamRepository = raceteamRepository;
        this.teamRepository = teamRepository;
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public RaceTeamCreateForm getEditRaceTeam(String id) {
        return mapper.map(raceteamRepository.findById(id), RaceTeamCreateForm.class);
    }

    @Override
    public void editRaceTeam(String id, RaceTeamCreateForm raceteamCreateForm) {
        RaceTeam raceteam = mapper.map(raceteamCreateForm, RaceTeam.class);
        raceteam.setId(id);
        raceteamRepository.save(raceteam);
    }

    @Override
    public void createRaceTeam(RaceTeamCreateForm raceteamCreateForm, String raceId) {
        RaceTeam raceteam = new RaceTeam();
        raceteam.setTeam(teamRepository.findById(raceteamCreateForm.getTeamId()).orElse(null));
        raceteam.setDriver(driverRepository.findById(raceteamCreateForm.getDriverId()).orElse(null));
        raceteam.setCar(carRepository.findById(raceteamCreateForm.getCarId()).orElse(null));
        raceteam.setRace(raceRepository.findById(raceId).orElse(null));
        raceteamRepository.save(raceteam);
    }

    @Override
    public void deleteRaceTeam(String id) {
        raceteamRepository.deleteById(id);
    }

    @Override
    public void saveAllRaceTeams(List<RaceTeam> raceteams) {
        raceteamRepository.saveAll(raceteams);
    }
}
