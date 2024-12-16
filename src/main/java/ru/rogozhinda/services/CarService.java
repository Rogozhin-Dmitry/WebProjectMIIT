package ru.rogozhinda.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rogozhinda.dto.car.*;
import ru.rogozhinda.entities.Car;
import ru.rogozhinda.entities.Team;

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

    List<CarSmallViewModel> getCarsSmall();

    void setCars(Team team, List<String> teamCarsIds);
}
