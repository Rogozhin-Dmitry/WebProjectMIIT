package ru.rogozhinda.controllers.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rogozhinda.controllers.HomeController;
import ru.rogozhinda.services.DriverService;
import ru.rogozhinda.services.RaceService;

@Controller
public class HomeControllerImpl implements HomeController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private RaceService raceService;

    @Override
    public String index(Model model) {
        model.addAttribute("topFiveDrivers", driverService.getTopForHome());
        model.addAttribute("nextRaces", raceService.getNextRaces());
        return "base/index";
    }
}
