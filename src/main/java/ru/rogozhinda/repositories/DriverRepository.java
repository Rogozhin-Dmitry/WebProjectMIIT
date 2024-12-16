package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Driver;

import java.util.List;

@Repository
public interface DriverRepository extends CrudRepository<Driver, String> {
    @Query("SELECT d FROM Driver d WHERE d.team IS NULL")
    List<Driver> findDriversWithoutTeam();

    Page<Driver> findAll(Pageable pageable);

    @Query("SELECT d FROM Driver d WHERE LOWER(d.name) LIKE %:query%")
    Page<Driver> findByFilter(Pageable pageable, @Param("query") String query);

    List<Driver> findAll();
}
