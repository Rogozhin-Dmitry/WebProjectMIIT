package ru.rogozhinda.dto.result;

import ru.rogozhinda.dto.base.BaseViewModel;

public record ResultDetailsViewModel(
        BaseViewModel base,
        ResultViewModel result,
        Integer laps,
        Integer lapTime,
        Integer gap,
        Integer points,
        String status
) {
}
