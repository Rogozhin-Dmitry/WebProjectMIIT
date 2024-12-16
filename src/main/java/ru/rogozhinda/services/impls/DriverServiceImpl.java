package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.driver.*;
import ru.rogozhinda.dto.team.TeamSmallViewModel;
import ru.rogozhinda.entities.Driver;
import ru.rogozhinda.entities.Team;
import ru.rogozhinda.repositories.DriverRepository;
import ru.rogozhinda.services.DriverService;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final ModelMapper mapper;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Page<DriverViewModel> getDrivers(Pageable pageable) {
        return driverRepository.findAll(pageable).map(driver -> mapper.map(driver, DriverViewModel.class));
    }

    @Override
    public Page<DriverViewModel> getDriversByFilter(Pageable pageable, DriversSearchForm form) {
        return driverRepository.findByFilter(pageable, form.getSearchTerm()).map(driver -> mapper.map(driver, DriverViewModel.class));
    }

    @Override
    public List<DriverSmallViewModel> getDriversSmall() {
        return driverRepository.findDriversWithoutTeam().stream().map(driver -> mapper.map(driver, DriverSmallViewModel.class)).toList();
    }

    @Override
    public List<DriverSmallViewModel> getDriversSmallAll() {
        return driverRepository.findAll().stream().map(driver -> mapper.map(driver, DriverSmallViewModel.class)).toList();
    }

    @Override
    public long countDrivers() {
        return driverRepository.count();
    }

    @Override
    public DriverDetailsViewModel getDriver(String id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if (driverOptional.isPresent()) {
            Driver driver = driverOptional.get();
            return mapDriverDetail(driver);
        } else {
            return null;
        }
    }

    @Override
    public DriverCreateForm getEditDriver(String id) {
        return mapper.map(driverRepository.findById(id), DriverCreateForm.class);
    }

    @Override
    public void editDriver(String id, DriverCreateForm driverCreateForm) {
        Driver driver = mapper.map(driverCreateForm, Driver.class);
        driver.setId(id);
        driverRepository.save(driver);
    }

    @Override
    public void createDriver(DriverCreateForm driverCreateForm) {
        Driver driver = mapper.map(driverCreateForm, Driver.class);
        driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(String id) {
        driverRepository.deleteById(id);
    }

    @Override
    public void saveAllDrivers(List<Driver> drivers) {
        driverRepository.saveAll(drivers);
    }

    private DriverDetailsViewModel mapDriverDetail(Driver driver) {
        BaseViewModel title = new BaseViewModel(driver.getName());
        DriverViewModel model = mapper.map(driver, DriverViewModel.class);
        return new DriverDetailsViewModel(title, model, driver.getHeight(), driver.getWeight(), driver.getBirthday());
    }

    @Override
    public void setDrivers(Team team, List<String> teamDriversIds) {
        Iterable<Driver> drivers = driverRepository.findAllById(teamDriversIds);
        for (Driver driver : drivers) {
            driver.setTeam(team);
        }
        driverRepository.saveAll(drivers);
    }
}
