package ru.rogozhinda.controllers.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.controllers.CarController;
import ru.rogozhinda.dto.car.CarCreateForm;
import ru.rogozhinda.dto.car.CarDetailsViewModel;
import ru.rogozhinda.dto.car.CarViewModel;
import ru.rogozhinda.dto.car.CarsSearchForm;
import ru.rogozhinda.services.CarService;

@Controller
public class CarControllerImpl implements CarController {
    private static final int pageSize = 5;

    @Autowired
    private CarService carService;

    @Override
    public String listCars(Integer page, Model model) {
        CarsSearchForm carsSearchForm = (CarsSearchForm) model.asMap().get("carsSearchForm");
        Page<CarViewModel> carList;
        if (carsSearchForm == null) {
            carsSearchForm = new CarsSearchForm();
            carList = carService.getCars(PageRequest.of(page, pageSize));
            if (carList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        } else {
            carList = carService.getCarsByFilter(PageRequest.of(page, pageSize), carsSearchForm);
            if (carList.isEmpty()) {
                model.addAttribute("carsSearchError", "not found");
            }
        }
        model.addAttribute("carList", carList);
        model.addAttribute("pageCount", Math.ceil((double) carService.countCars() / pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("CarsSearchForm", carsSearchForm);
        return "car/car-list";
    }

    @Override
    public String listCarsSearch(CarsSearchForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("carsSearchForm", form);
        return "redirect:/cars";
    }

    @Override
    public String details(String id, Model model) {
        CarDetailsViewModel detailsViewModel = carService.getCar(id);
        if (detailsViewModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        model.addAttribute("carDetails", detailsViewModel);
        return "car/car-details";
    }

    @Override
    public String createForm(Model model) {
        CarCreateForm carCreateForm = (CarCreateForm) model.asMap().get("carCreateForm");
        if (carCreateForm == null) {
            carCreateForm = new CarCreateForm();
        }
        model.addAttribute("carCreateForm", carCreateForm);
        return "car/car-create";
    }

    @Override
    public String create(CarCreateForm carCreateForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("carCreateForm", carCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.carCreateForm", bindingResult);
            return "redirect:/cars/create";
        }
        carService.createCar(carCreateForm);
        return "redirect:/cars";
    }

    @Override
    public String editForm(String id, Model model) {
        CarCreateForm carEditForm = (CarCreateForm) model.asMap().get("carEditForm");
        if (carEditForm == null) {
            carEditForm = carService.getEditCar(id);
            if (carEditForm == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
        model.addAttribute("carEditForm", carEditForm);
        model.addAttribute("carEditId", id);
        return "car/car-edit";
    }

    @Override
    public String edit(String id, CarCreateForm carEditForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("carEditForm", carEditForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.carEditForm", bindingResult);
            model.addAttribute("carEditId", id);
            return "redirect:/cars/" + id + "/edit";
        }
        carService.editCar(id, carEditForm);
        return "redirect:/cars/" + id;
    }

    @Override
    public String delete(String id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

}