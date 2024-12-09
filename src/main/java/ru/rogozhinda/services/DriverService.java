package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.driver.DriverDetailsViewModel;
import ru.rogozhinda.dto.driver.DriverViewModel;
import ru.rogozhinda.entities.Driver;


import java.util.List;

public interface DriverService {
    Page<DriverViewModel> getDrivers(Pageable pageable);

    DriverDetailsViewModel getDriver(Integer id);

    DriverDetailsViewModel createDriver(Driver driver);

    void deleteDriver(Integer id);

    void saveAllDrivers(List<Driver> books);
}
