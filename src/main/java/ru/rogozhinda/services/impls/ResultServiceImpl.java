package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.result.ResultCreateForm;
import ru.rogozhinda.dto.result.ResultDetailsViewModel;
import ru.rogozhinda.dto.result.ResultViewModel;
import ru.rogozhinda.entities.Result;
import ru.rogozhinda.repositories.ResultRepository;
import ru.rogozhinda.services.ResultService;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ModelMapper mapper;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
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
    public void createResult(ResultCreateForm resultCreateForm) {
        Result result = mapper.map(resultCreateForm, Result.class);
        resultRepository.save(result);
    }

    @Override
    public void deleteResult(String id) {
        resultRepository.deleteById(id);
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
