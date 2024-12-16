package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.driver.*;
import ru.rogozhinda.entities.Driver;
import ru.rogozhinda.entities.Team;

import java.util.List;

public interface DriverService {
    Page<DriverViewModel> getDrivers(Pageable pageable);

    Page<DriverViewModel> getDriversByFilter(Pageable pageable, DriversSearchForm form);

    List<DriverSmallViewModel> getDriversSmall();

    long countDrivers();

    DriverDetailsViewModel getDriver(String id);

    DriverCreateForm getEditDriver(String id);

    void editDriver(String id, DriverCreateForm driverCreateForm);

    void createDriver(DriverCreateForm driverCreateForm);

    void deleteDriver(String id);

    void saveAllDrivers(List<Driver> drivers);

    void setDrivers(Team team, List<String> teamDriversIds);

}
