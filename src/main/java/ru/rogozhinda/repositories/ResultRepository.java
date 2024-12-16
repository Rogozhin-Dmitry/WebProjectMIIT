package ru.rogozhinda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, String> {
    Page<Result> findAll(Pageable pageable);
}
