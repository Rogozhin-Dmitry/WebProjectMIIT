package ru.rogozhinda.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.RaceTeam;

@Repository
public interface RaceTeamRepository extends CrudRepository<RaceTeam, Integer> {
}
