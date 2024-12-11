package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.race.RaceDetailsViewModel;
import ru.rogozhinda.dto.race.RaceEditForm;
import ru.rogozhinda.dto.race.RaceViewModel;
import ru.rogozhinda.entities.Race;
import ru.rogozhinda.repositories.RaceRepository;
import ru.rogozhinda.services.RaceService;

import java.util.List;

public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final ModelMapper mapper;

    public RaceServiceImpl(RaceRepository raceRepository, ModelMapper mapper) {
        this.raceRepository = raceRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<RaceViewModel> getRaces(Pageable pageable) {
        return raceRepository.findAll(pageable).map(race -> mapper.map(race, RaceViewModel.class));
    }

    @Override
    public RaceDetailsViewModel getRace(Integer id) {
        return mapper.map(raceRepository.findById(id), RaceDetailsViewModel.class);
    }

    @Override
    public RaceDetailsViewModel createRace(RaceEditForm raceEditForm) {
        Race race = mapper.map(raceEditForm, Race.class);
        race = raceRepository.save(race);
        return mapper.map(race, RaceDetailsViewModel.class);
    }

    @Override
    public void deleteRace(Integer id) {
        raceRepository.deleteById(id);
    }

    @Override
    public void saveAllRaces(List<Race> books) {

    }
}
