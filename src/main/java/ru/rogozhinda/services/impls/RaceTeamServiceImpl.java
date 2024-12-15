package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import ru.rogozhinda.dto.raceteam.RaceTeamDetailsViewModel;
//import ru.rogozhinda.dto.raceteam.RaceTeamEditForm;
import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;
import ru.rogozhinda.entities.RaceTeam;
import ru.rogozhinda.repositories.RaceTeamRepository;
import ru.rogozhinda.services.RaceTeamService;

import java.util.List;

public class RaceTeamServiceImpl implements RaceTeamService {
    private final RaceTeamRepository raceteamRepository;
    private final ModelMapper mapper;

    public RaceTeamServiceImpl(RaceTeamRepository raceteamRepository, ModelMapper mapper) {
        this.raceteamRepository = raceteamRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<RaceTeamViewModel> getRaceTeams(Pageable pageable) {
        return raceteamRepository.findAll(pageable).map(raceteam -> mapper.map(raceteam, RaceTeamViewModel.class));
    }
//
//    @Override
//    public RaceTeamDetailsViewModel getRaceTeam(Integer id) {
//        return mapper.map(raceteamRepository.findById(id), RaceTeamDetailsViewModel.class);
//    }
//
//    @Override
//    public RaceTeamDetailsViewModel createRaceTeam(RaceTeamEditForm raceteamEditForm) {
//        RaceTeam raceteam = mapper.map(raceteamEditForm, RaceTeam.class);
//        raceteam = raceteamRepository.save(raceteam);
//        return mapper.map(raceteam, RaceTeamDetailsViewModel.class);
//    }

    @Override
    public void deleteRaceTeam(Integer id) {
        raceteamRepository.deleteById(id);
    }

    @Override
    public void saveAllRaceTeams(List<RaceTeam> books) {

    }
}
