package ru.rogozhinda.dto.car;

import ru.rogozhinda.dto.base.BaseViewModel;

import java.util.List;

public record CarListViewModel(
        BaseViewModel base,
        List<CarViewModel> cars,
        Integer totalPages
) {
}
