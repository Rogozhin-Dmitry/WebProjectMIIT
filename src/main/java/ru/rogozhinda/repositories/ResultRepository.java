package ru.rogozhinda.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
}
