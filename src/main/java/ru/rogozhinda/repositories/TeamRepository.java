package ru.rogozhinda.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
}
