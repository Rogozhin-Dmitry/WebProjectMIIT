package ru.rogozhinda.services.impls;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.car.CarDetailsViewModel;
import ru.rogozhinda.dto.car.CarEditForm;
import ru.rogozhinda.dto.car.CarViewModel;
import ru.rogozhinda.entities.Car;
import ru.rogozhinda.repositories.CarRepository;
import ru.rogozhinda.services.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<CarViewModel> getCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(car -> mapper.map(car, CarViewModel.class));
    }

    @Override
    public CarDetailsViewModel getCar(Integer id) {
        return mapper.map(carRepository.findById(id), CarDetailsViewModel.class);
    }

    @Override
    public CarDetailsViewModel createCar(CarEditForm carEditForm) {
        Car car = mapper.map(carEditForm, Car.class);
        car = carRepository.save(car);
        return mapper.map(car, CarDetailsViewModel.class);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }

    @Override
    public void saveAllCars(List<Car> books) {

    }
}
