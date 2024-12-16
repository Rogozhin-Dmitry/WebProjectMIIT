package ru.rogozhinda.controllers.impls;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RaceTeamService raceteamService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private ResultService resultService;


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
        System.out.println(carsList);

        model.addAttribute("raceteamCreateForm", raceteamCreateForm);
        model.addAttribute("raceId", raceId);
        return "raceteam/raceteam-create";
    }

    @Override
    public String create(RaceTeamCreateForm raceteamCreateForm, BindingResult bindingResult, Model model, String raceId, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("raceteamCreateForm", raceteamCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.raceteamCreateForm", bindingResult);
            return "redirect:/raceteams/create?raceId=" + raceId;
        }
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
        raceteamService.editRaceTeam(id, raceteamEditForm);
        return "redirect:/raceteams/" + id;
    }

    @Override
    public String delete(String id) {
        raceteamService.deleteRaceTeam(id);
        return "redirect:/raceteams"; // TODO
    }

}
