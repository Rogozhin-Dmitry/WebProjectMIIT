package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Team;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
    Page<Team> findAll(Pageable pageable);

    List<Team> findAll();


    @Query("SELECT t FROM Team t WHERE LOWER(t.name) LIKE %:query%")
    Page<Team> findByFilter(Pageable pageable, @Param("query") String query);
}
