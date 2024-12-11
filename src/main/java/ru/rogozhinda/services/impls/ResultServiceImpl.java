package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.result.ResultDetailsViewModel;
import ru.rogozhinda.dto.result.ResultEditForm;
import ru.rogozhinda.dto.result.ResultViewModel;
import ru.rogozhinda.entities.Result;
import ru.rogozhinda.repositories.ResultRepository;
import ru.rogozhinda.services.ResultService;

import java.util.List;

public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ModelMapper mapper;

    public ResultServiceImpl(ResultRepository resultRepository, ModelMapper mapper) {
        this.resultRepository = resultRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<ResultViewModel> getResults(Pageable pageable) {
        return resultRepository.findAll(pageable).map(result -> mapper.map(result, ResultViewModel.class));
    }

    @Override
    public ResultDetailsViewModel getResult(Integer id) {
        return mapper.map(resultRepository.findById(id), ResultDetailsViewModel.class);
    }

    @Override
    public ResultDetailsViewModel createResult(ResultEditForm resultEditForm) {
        Result result = mapper.map(resultEditForm, Result.class);
        result = resultRepository.save(result);
        return mapper.map(result, ResultDetailsViewModel.class);
    }

    @Override
    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }

    @Override
    public void saveAllResults(List<Result> books) {

    }
}
