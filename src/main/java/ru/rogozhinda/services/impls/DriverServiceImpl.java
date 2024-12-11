package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.driver.DriverDetailsViewModel;
import ru.rogozhinda.dto.driver.DriverEditForm;
import ru.rogozhinda.dto.driver.DriverViewModel;
import ru.rogozhinda.entities.Driver;
import ru.rogozhinda.repositories.DriverRepository;
import ru.rogozhinda.services.DriverService;

import java.util.List;

public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final ModelMapper mapper;

    public DriverServiceImpl(DriverRepository driverRepository, ModelMapper mapper) {
        this.driverRepository = driverRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<DriverViewModel> getDrivers(Pageable pageable) {
        return driverRepository.findAll(pageable).map(driver -> mapper.map(driver, DriverViewModel.class));
    }

    @Override
    public DriverDetailsViewModel getDriver(Integer id) {
        return mapper.map(driverRepository.findById(id), DriverDetailsViewModel.class);
    }

    @Override
    public DriverDetailsViewModel createDriver(DriverEditForm driverEditForm) {
        Driver driver = mapper.map(driverEditForm, Driver.class);
        driver = driverRepository.save(driver);
        return mapper.map(driver, DriverDetailsViewModel.class);
    }

    @Override
    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
    }

    @Override
    public void saveAllDrivers(List<Driver> books) {

    }
}
