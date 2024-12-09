package ru.rogozhinda.dto.result;

import ru.rogozhinda.dto.base.BaseViewModel;

import java.util.List;

public record ResultListViewModel(
        BaseViewModel base,
        List<ResultViewModel> results,
        Integer totalPages
) {
}
