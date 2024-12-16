//package ru.rogozhinda.services.impls;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import ru.rogozhinda.dto.base.BaseViewModel;
//import ru.rogozhinda.dto.raceteam.RaceTeamCreateForm;
//import ru.rogozhinda.dto.raceteam.RaceTeamDetailsViewModel;
//import ru.rogozhinda.dto.raceteam.RaceTeamViewModel;
//import ru.rogozhinda.dto.raceteam.RaceTeamsSearchForm;
//import ru.rogozhinda.entities.RaceTeam;
//import ru.rogozhinda.repositories.RaceTeamRepository;
//import ru.rogozhinda.services.RaceTeamService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class RaceTeamServiceImpl implements RaceTeamService {
//    private final RaceTeamRepository raceteamRepository;
//    private final ModelMapper mapper;
//
//    @Autowired
//    public RaceTeamServiceImpl(RaceTeamRepository raceteamRepository) {
//        this.raceteamRepository = raceteamRepository;
//        this.mapper = new ModelMapper();
//    }
//
//    @Override
//    public Page<RaceTeamViewModel> getRaceTeams(Pageable pageable) {
//        return raceteamRepository.findAll(pageable).map(raceteam -> mapper.map(raceteam, RaceTeamViewModel.class));
//    }
//
//    @Override
//    public long countRaceTeams() {
//        return raceteamRepository.count();
//    }
//
//    @Override
//    public RaceTeamCreateForm getEditRaceTeam(String id) {
//        return mapper.map(raceteamRepository.findById(id), RaceTeamCreateForm.class);
//    }
//
//    @Override
//    public void editRaceTeam(String id, RaceTeamCreateForm raceteamCreateForm) {
//        RaceTeam raceteam = mapper.map(raceteamCreateForm, RaceTeam.class);
//        raceteam.setId(id);
//        raceteamRepository.save(raceteam);
//    }
//
//    @Override
//    public void createRaceTeam(RaceTeamCreateForm raceteamCreateForm) {
//        RaceTeam raceteam = mapper.map(raceteamCreateForm, RaceTeam.class);
//        raceteamRepository.save(raceteam);
//    }
//
//    @Override
//    public void deleteRaceTeam(String id) {
//        raceteamRepository.deleteById(id);
//    }
//
//    @Override
//    public void saveAllRaceTeams(List<RaceTeam> raceteams) {
//        raceteamRepository.saveAll(raceteams);
//    }
//
//    private RaceTeamDetailsViewModel mapRaceTeamDetail(RaceTeam raceteam) {
//        BaseViewModel title = new BaseViewModel(raceteam.getModel());
//        RaceTeamViewModel model = mapper.map(raceteam, RaceTeamViewModel.class);
//        return new RaceTeamDetailsViewModel(title, model, raceteam.getEngine(), raceteam.getWeight());
//    }
//}
