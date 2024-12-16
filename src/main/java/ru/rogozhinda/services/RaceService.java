package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.race.RaceCreateForm;
import ru.rogozhinda.dto.race.RaceDetailsViewModel;
import ru.rogozhinda.dto.race.RaceViewModel;
import ru.rogozhinda.dto.race.RacesSearchForm;
import ru.rogozhinda.entities.Race;

import java.util.List;

public interface RaceService {
    Page<RaceViewModel> getRaces(Pageable pageable);

    Page<RaceViewModel> getRacesByFilter(Pageable pageable, RacesSearchForm form);

    long countRaces();

    RaceDetailsViewModel getRace(String id);

    RaceCreateForm getEditRace(String id);

    void editRace(String id, RaceCreateForm raceCreateForm);

    void createRace(RaceCreateForm raceCreateForm);

    void deleteRace(String id);

    void saveAllRaces(List<Race> races);
}
