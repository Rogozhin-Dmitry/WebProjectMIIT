package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.result.ResultDetailsViewModel;
import ru.rogozhinda.dto.result.ResultViewModel;
import ru.rogozhinda.entities.Result;


import java.util.List;

public interface ResultService {
    Page<ResultViewModel> getResults(Pageable pageable);

    ResultDetailsViewModel getResult(Integer id);

    ResultDetailsViewModel createResult(Result result);

    void deleteResult(Integer id);

    void saveAllResults(List<Result> books);
}
