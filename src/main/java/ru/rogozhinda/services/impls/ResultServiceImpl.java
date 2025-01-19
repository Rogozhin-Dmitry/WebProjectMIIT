package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.result.ResultCreateForm;
import ru.rogozhinda.dto.result.ResultDetailsViewModel;
import ru.rogozhinda.dto.result.ResultViewModel;
import ru.rogozhinda.entities.RaceTeam;
import ru.rogozhinda.entities.Result;
import ru.rogozhinda.repositories.RaceTeamRepository;
import ru.rogozhinda.repositories.ResultRepository;
import ru.rogozhinda.services.ResultService;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final RaceTeamRepository raceTeamRepository;

    private final ModelMapper mapper;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, RaceTeamRepository raceTeamRepository) {
        this.resultRepository = resultRepository;
        this.raceTeamRepository = raceTeamRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public ResultDetailsViewModel getResult(String id) {
        Optional<Result> resultOptional = resultRepository.findById(id);
        if (resultOptional.isPresent()) {
            Result result = resultOptional.get();
            return mapResultDetail(result);
        } else {
            return null;
        }
    }

    @Override
    public ResultCreateForm getEditResult(String id) {
        return mapper.map(resultRepository.findById(id), ResultCreateForm.class);
    }

    @Override
    public void editResult(String id, ResultCreateForm resultCreateForm) {
        Result result = mapper.map(resultCreateForm, Result.class);
        result.setId(id);
        resultRepository.save(result);
    }

    @Override
    public void createResult(ResultCreateForm resultCreateForm, String raceTeamId) {
        Result result = mapper.map(resultCreateForm, Result.class);
        Optional<RaceTeam> raceTeam = raceTeamRepository.findById(raceTeamId);
        result = resultRepository.save(result);
        if (raceTeam.isPresent()) {
            RaceTeam raceTeamObj = raceTeam.get();
            raceTeamObj.setResult(result);
            raceTeamRepository.save(raceTeamObj);
        }
    }

    @Override
    public void deleteResult(String id) {
        Optional<Result> result = resultRepository.findById(id);
        result.ifPresent(value -> value.getRaceTeam().setResult(null));
        resultRepository.deleteById(id);
    }

    @Override
    public String getResultRaceId(String id) {
        Optional<Result> result = resultRepository.findById(id);
        return result.map(value -> value.getRaceTeam().getRace().getId()).orElse("");
    }

    @Override
    public void saveAllResults(List<Result> results) {
        resultRepository.saveAll(results);
    }

    private ResultDetailsViewModel mapResultDetail(Result result) {
        BaseViewModel title = new BaseViewModel(result.getStatus());
        ResultViewModel model = mapper.map(result, ResultViewModel.class);
        return new ResultDetailsViewModel(
                title, model,
                result.getLaps(),
                result.getLapTime(),
                result.getGap(),
                result.getPoints(),
                result.getStatus());
    }
}
