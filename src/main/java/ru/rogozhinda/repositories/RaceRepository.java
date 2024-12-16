package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Race;

@Repository
public interface RaceRepository extends CrudRepository<Race, String> {
    Page<Race> findAll(Pageable pageable);

    @Query("SELECT r FROM Race r WHERE LOWER(r.name) LIKE %:query%")
    Page<Race> findByFilter(Pageable pageable, @Param("query") String query);
}
