package ru.rogozhinda.dto.driver;

import ru.rogozhinda.dto.base.BaseViewModel;

import java.util.List;

public record DriverListViewModel(
        BaseViewModel base,
        List<DriverViewModel> drivers,
        Integer totalPages
) {
}
