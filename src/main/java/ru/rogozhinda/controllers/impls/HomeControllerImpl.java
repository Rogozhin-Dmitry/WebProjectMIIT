package ru.rogozhinda.controllers.impls;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rogozhinda.controllers.HomeController;
import ru.rogozhinda.services.DriverService;
import ru.rogozhinda.services.RaceService;

@Controller
public class HomeControllerImpl implements HomeController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final DriverService driverService;
    private final RaceService raceService;

    public HomeControllerImpl(DriverService driverService, RaceService raceService) {
        this.driverService = driverService;
        this.raceService = raceService;
    }

    @Override
    public String index(Model model) {
        model.addAttribute("topFiveDrivers", driverService.getTopForHome());
        model.addAttribute("nextRaces", raceService.getNextRaces());
        LOG.log(Level.INFO, "getHomePage");
        return "base/index";
    }
}
