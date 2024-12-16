package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.RaceTeam;

@Repository
public interface RaceTeamRepository extends CrudRepository<RaceTeam, String> {
    Page<RaceTeam> findAll(Pageable pageable);
}
