package ru.rogozhinda.controllers.impls;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.controllers.RaceTeamController;
import ru.rogozhinda.dto.car.CarSmallViewModel;
import ru.rogozhinda.dto.driver.DriverSmallViewModel;
import ru.rogozhinda.dto.raceteam.RaceTeamCreateForm;
import ru.rogozhinda.dto.team.TeamSmallViewModel;
import ru.rogozhinda.services.*;

import java.util.List;

@Controller
public class RaceTeamControllerImpl implements RaceTeamController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final RaceTeamService raceteamService;
    private final TeamService teamService;
    private final CarService carService;
    private final DriverService driverService;

    public RaceTeamControllerImpl(RaceTeamService raceteamService, TeamService teamService, CarService carService, DriverService driverService, ResultService resultService) {
        this.raceteamService = raceteamService;
        this.teamService = teamService;
        this.carService = carService;
        this.driverService = driverService;
    }

    @Override
    public String createForm(Model model, String raceId) {
        RaceTeamCreateForm raceteamCreateForm = (RaceTeamCreateForm) model.asMap().get("raceteamCreateForm");
        if (raceteamCreateForm == null) {
            raceteamCreateForm = new RaceTeamCreateForm();
        }

        List<TeamSmallViewModel> teamsList = teamService.getTeamsSmallAll();
        model.addAttribute("teamsList", teamsList);

        List<DriverSmallViewModel> driversList = driverService.getDriversSmallAll();
        model.addAttribute("driversList", driversList);

        List<CarSmallViewModel> carsList = carService.getCarsSmallAll();
        model.addAttribute("carsList", carsList);

        model.addAttribute("raceteamCreateForm", raceteamCreateForm);
        model.addAttribute("raceId", raceId);
        LOG.log(Level.INFO, "GetRaceTeamCreate");
        return "raceteam/raceteam-create";
    }

    @Override
    public String create(RaceTeamCreateForm raceteamCreateForm, BindingResult bindingResult, Model model, String raceId, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("raceteamCreateForm", raceteamCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.raceteamCreateForm", bindingResult);
            return "redirect:/raceteams/create?raceId=" + raceId;
        }
        LOG.log(Level.INFO, "PostRaceTeamCreate");
        raceteamService.createRaceTeam(raceteamCreateForm, raceId);
        return "redirect:/races/" + raceId;
    }

    @Override
    public String editForm(String id, Model model) {
        RaceTeamCreateForm raceteamEditForm = (RaceTeamCreateForm) model.asMap().get("raceteamEditForm");
        if (raceteamEditForm == null) {
            raceteamEditForm = raceteamService.getEditRaceTeam(id);
            if (raceteamEditForm == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
        LOG.log(Level.INFO, "GetRaceTeamEdit");
        model.addAttribute("raceteamEditForm", raceteamEditForm);
        model.addAttribute("raceteamEditId", id);
        return "raceteam/raceteam-edit";
    }

    @Override
    public String edit(String id, RaceTeamCreateForm raceteamEditForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("raceteamEditForm", raceteamEditForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.raceteamEditForm", bindingResult);
            model.addAttribute("raceteamEditId", id);
            return "redirect:/raceteams/" + id + "/edit";
        }
        LOG.log(Level.INFO, "PostRaceTeamEdit" + id);
        raceteamService.editRaceTeam(id, raceteamEditForm);
        return "redirect:/raceteams/" + id;
    }

    @Override
    public String delete(String id, String raceId) {
        raceteamService.deleteRaceTeam(id);
        LOG.log(Level.INFO, "GetRaceTeamDelete" + id);
        return "redirect:/races/" + raceId;
    }

}
