package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.car.CarDetailsViewModel;
import ru.rogozhinda.dto.car.CarViewModel;
import ru.rogozhinda.entities.Car;


import java.util.List;

public interface CarService {
    Page<CarViewModel> getCars(Pageable pageable);

    CarDetailsViewModel getCar(Integer id);

    CarDetailsViewModel createCar(Car car);

    void deleteCar(Integer id);

    void saveAllCars(List<Car> books);
}
