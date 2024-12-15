package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {
    Page<Car> findAll(Pageable pageable);

    @Query("SELECT c FROM Car c WHERE c.model LIKE %:query%")
    Page<Car> findByFilter(Pageable pageable, @Param("query") String query);
}
