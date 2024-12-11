package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Driver;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer> {
    Page<Driver> findAll(Pageable pageable);
}
