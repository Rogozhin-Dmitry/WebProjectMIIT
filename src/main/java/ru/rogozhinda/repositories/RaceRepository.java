package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Race;

@Repository
public interface RaceRepository extends CrudRepository<Race, Integer> {
    Page<Race> findAll(Pageable pageable);
}
