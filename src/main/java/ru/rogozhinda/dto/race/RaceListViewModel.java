package ru.rogozhinda.dto.race;

import ru.rogozhinda.dto.base.BaseViewModel;

import java.util.List;

public record RaceListViewModel(
        BaseViewModel base,
        List<RaceViewModel> races,
        Integer totalPages
) {
}
