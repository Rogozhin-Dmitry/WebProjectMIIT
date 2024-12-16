package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.race.RaceCreateForm;
import ru.rogozhinda.dto.race.RaceDetailsViewModel;
import ru.rogozhinda.dto.race.RaceViewModel;
import ru.rogozhinda.dto.race.RacesSearchForm;
import ru.rogozhinda.entities.Race;
import ru.rogozhinda.repositories.RaceRepository;
import ru.rogozhinda.services.RaceService;

import java.util.List;
import java.util.Optional;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final ModelMapper mapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Page<RaceViewModel> getRaces(Pageable pageable) {
        return raceRepository.findAll(pageable).map(race -> mapper.map(race, RaceViewModel.class));
    }

    @Override
    public Page<RaceViewModel> getRacesByFilter(Pageable pageable, RacesSearchForm form) {
        return raceRepository.findByFilter(pageable, form.getSearchTerm()).map(race -> mapper.map(race, RaceViewModel.class));
    }

    @Override
    public long countRaces() {
        return raceRepository.count();
    }

    @Override
    public RaceDetailsViewModel getRace(String id) {
        Optional<Race> raceOptional = raceRepository.findById(id);
        if (raceOptional.isPresent()) {
            Race race = raceOptional.get();
            return mapRaceDetail(race);
        } else {
            return null;
        }
    }

    @Override
    public RaceCreateForm getEditRace(String id) {
        return mapper.map(raceRepository.findById(id), RaceCreateForm.class);
    }

    @Override
    public void editRace(String id, RaceCreateForm raceCreateForm) {
        Race race = mapper.map(raceCreateForm, Race.class);
        race.setId(id);
        raceRepository.save(race);
    }

    @Override
    public void createRace(RaceCreateForm raceCreateForm) {
        Race race = mapper.map(raceCreateForm, Race.class);
        raceRepository.save(race);
    }

    @Override
    public void deleteRace(String id) {
        raceRepository.deleteById(id);
    }

    @Override
    public void saveAllRaces(List<Race> races) {
        raceRepository.saveAll(races);
    }

    private RaceDetailsViewModel mapRaceDetail(Race race) {
        BaseViewModel title = new BaseViewModel(race.getName());
        RaceViewModel model = mapper.map(race, RaceViewModel.class);
        return new RaceDetailsViewModel(title, model, race.getCircuit(), race.getBroadcasters());
    }
}
