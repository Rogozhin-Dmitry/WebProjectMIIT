package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.race.RaceDetailsViewModel;
import ru.rogozhinda.dto.race.RaceViewModel;
import ru.rogozhinda.entities.Race;


import java.util.List;

public interface RaceService {
    Page<RaceViewModel> getRaces(Pageable pageable);

    RaceDetailsViewModel getRace(Integer id);

    RaceDetailsViewModel createRace(Race race);

    void deleteRace(Integer id);

    void saveAllRaces(List<Race> books);
}
