package ru.rogozhinda.dto.team;

import ru.rogozhinda.dto.base.BaseViewModel;

import java.util.List;

public record TeamListViewModel(
        BaseViewModel base,
        List<TeamViewModel> teams,
        Integer totalPages
) {
}
