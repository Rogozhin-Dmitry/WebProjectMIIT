package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.base.BaseViewModel;
import ru.rogozhinda.dto.car.*;
import ru.rogozhinda.dto.driver.DriverSmallViewModel;
import ru.rogozhinda.entities.Car;
import ru.rogozhinda.entities.Driver;
import ru.rogozhinda.entities.Team;
import ru.rogozhinda.repositories.CarRepository;
import ru.rogozhinda.services.CarService;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Page<CarViewModel> getCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(car -> mapper.map(car, CarViewModel.class));
    }

    @Override
    public Page<CarViewModel> getCarsByFilter(Pageable pageable, CarsSearchForm form) {
        return carRepository.findByFilter(pageable, form.getSearchTerm()).map(car -> mapper.map(car, CarViewModel.class));
    }

    @Override
    public long countCars() {
        return carRepository.count();
    }

    @Override
    public CarDetailsViewModel getCar(String id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return mapCarDetail(car);
        } else {
            return null;
        }
    }

    @Override
    public CarCreateForm getEditCar(String id) {
        return mapper.map(carRepository.findById(id), CarCreateForm.class);
    }

    @Override
    public void editCar(String id, CarCreateForm carCreateForm) {
        Car car = mapper.map(carCreateForm, Car.class);
        car.setId(id);
        carRepository.save(car);
    }

    @Override
    public void createCar(CarCreateForm carCreateForm) {
        Car car = mapper.map(carCreateForm, Car.class);
        carRepository.save(car);
    }

    @Override
    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    @Override
    public void saveAllCars(List<Car> cars) {
        carRepository.saveAll(cars);
    }

    @Override
    public List<CarSmallViewModel> getCarsSmall() {
        return carRepository.findCarsWithoutTeam().stream().map(car -> mapper.map(car, CarSmallViewModel.class)).toList();
    }

    @Override
    public void setCars(Team team, List<String> teamCarsIds) {
        Iterable<Car> cars = carRepository.findAllById(teamCarsIds);
        for (Car car : cars) {
            car.setTeam(team);
        }
        carRepository.saveAll(cars);
    }

    private CarDetailsViewModel mapCarDetail(Car car) {
        BaseViewModel title = new BaseViewModel(car.getModel());
        CarViewModel model = mapper.map(car, CarViewModel.class);
        return new CarDetailsViewModel(title, model, car.getEngine(), car.getWeight());
    }
}
