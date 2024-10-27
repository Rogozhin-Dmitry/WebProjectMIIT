package ru.rogozhinda.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Race;

@Repository
public interface RaceRepository extends CrudRepository<Race, Integer> {
}
