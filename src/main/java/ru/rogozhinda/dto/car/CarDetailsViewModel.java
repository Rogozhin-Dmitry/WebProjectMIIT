package ru.rogozhinda.dto.car;

import ru.rogozhinda.dto.base.BaseViewModel;

public record CarDetailsViewModel(
        BaseViewModel base,
        CarViewModel car,
        String engine,
        Integer weight
) {
}
