package ru.rogozhinda.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
}
