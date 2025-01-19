package ru.rogozhinda.services;

import ru.rogozhinda.dto.result.ResultCreateForm;
import ru.rogozhinda.dto.result.ResultDetailsViewModel;
import ru.rogozhinda.entities.Result;

import java.util.List;

public interface ResultService {
    ResultDetailsViewModel getResult(String id);

    ResultCreateForm getEditResult(String id);

    void editResult(String id, ResultCreateForm resultCreateForm);

    void createResult(ResultCreateForm resultCreateForm, String raceTeamId);

    void deleteResult(String id);

    String getResultRaceId(String id);

    void saveAllResults(List<Result> results);
}
