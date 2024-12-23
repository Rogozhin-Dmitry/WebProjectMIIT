package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.dto.race.RaceViewModel;
import ru.rogozhinda.entities.Race;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RaceRepository extends CrudRepository<Race, String> {
    Page<Race> findAll(Pageable pageable);

    @Query("SELECT r FROM Race r WHERE LOWER(r.name) LIKE %:query%")
    Page<Race> findByFilter(Pageable pageable, @Param("query") String query);

    @Query("SELECT NEW " +
            "ru.rogozhinda.dto.race.RaceViewModel(race.id, race.name, race.location, race.duration, race.date) " +
            "FROM Race race WHERE race.date >= :today ORDER BY race.date ASC LIMIT 5")
    List<RaceViewModel> getNextRaces(@Param("today") Date today);
}
