package ru.rogozhinda.dto.driver;

import ru.rogozhinda.dto.base.BaseViewModel;

import java.util.Date;

public record DriverDetailsViewModel(
        BaseViewModel base,
        DriverViewModel driver,
        Integer height,
        Integer weight,
        Date birthday
) {
}
