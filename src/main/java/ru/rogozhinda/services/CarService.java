package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.car.CarCreateForm;
import ru.rogozhinda.dto.car.CarDetailsViewModel;
import ru.rogozhinda.dto.car.CarViewModel;
import ru.rogozhinda.dto.car.CarsSearchForm;
import ru.rogozhinda.entities.Car;

import java.util.List;

public interface CarService {
    Page<CarViewModel> getCars(Pageable pageable);

    Page<CarViewModel> getCarsByFilter(Pageable pageable, CarsSearchForm form);

    long countCars();

    CarDetailsViewModel getCar(String id);

    CarCreateForm getEditCar(String id);

    void editCar(String id, CarCreateForm carCreateForm);

    void createCar(CarCreateForm carCreateForm);

    void deleteCar(String id);

    void saveAllCars(List<Car> cars);
}
