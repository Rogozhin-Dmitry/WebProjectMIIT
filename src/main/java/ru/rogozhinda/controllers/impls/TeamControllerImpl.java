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
import ru.rogozhinda.controllers.TeamController;
import ru.rogozhinda.dto.car.CarSmallViewModel;
import ru.rogozhinda.dto.driver.DriverSmallViewModel;
import ru.rogozhinda.dto.team.TeamCreateForm;
import ru.rogozhinda.dto.team.TeamDetailsViewModel;
import ru.rogozhinda.dto.team.TeamViewModel;
import ru.rogozhinda.dto.team.TeamsSearchForm;
import ru.rogozhinda.services.CarService;
import ru.rogozhinda.services.DriverService;
import ru.rogozhinda.services.TeamService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamControllerImpl implements TeamController {
    private static final int pageSize = 5;

    @Autowired
    private TeamService teamService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private CarService carService;

    private static List<Boolean> getBooleansDrivers(List<Boolean> teamCreateList, List<DriverSmallViewModel> driversList) {
        List<Boolean> driverIds;
        if (teamCreateList == null || teamCreateList.isEmpty()) {
            driverIds = new ArrayList<>(driversList.size());
            for (int i = 0; i < driversList.size(); i++) {
                driverIds.add(false);
            }
        } else {
            driverIds = teamCreateList;
            for (int i = 0; i < driversList.size(); i++) {
                if (driverIds.size() > i && driverIds.get(i) == null) {
                    driverIds.set(i, false);
                } else if (driverIds.size() <= i) {
                    driverIds.add(false);
                }
            }
        }
        return driverIds;
    }

    private static List<Boolean> getBooleansCars(List<Boolean> teamCreateList, List<CarSmallViewModel> carsList) {
        List<Boolean> carIds;
        if (teamCreateList == null || teamCreateList.isEmpty()) {
            carIds = new ArrayList<>(carsList.size());
            for (int i = 0; i < carsList.size(); i++) {
                carIds.add(false);
            }
        } else {
            carIds = teamCreateList;
            for (int i = 0; i < carsList.size(); i++) {
                if (carIds.size() > i && carIds.get(i) == null) {
                    carIds.set(i, false);
                } else if (carIds.size() <= i) {
                    carIds.add(false);
                }
            }
        }
        return carIds;
    }

    private static List<String> getDriversByBooleansDrivers(List<Boolean> driverIds, List<DriverSmallViewModel> driversList) {
        List<String> addDriversList = new ArrayList<>();
        if (driverIds == null || driverIds.isEmpty()) {
            return addDriversList;
        }
        for (int i = 0; i < driverIds.size(); i++) {
            if (driverIds.get(i) != null && driverIds.get(i)) {
                addDriversList.add(driversList.get(i).getId());
            }
        }
        return addDriversList;
    }

    private static List<String> getDriversByBooleansCars(List<Boolean> carsIds, List<CarSmallViewModel> carsList) {
        List<String> addCarsList = new ArrayList<>();
        if (carsIds == null || carsIds.isEmpty()) {
            return addCarsList;
        }
        for (int i = 0; i < carsIds.size(); i++) {
            if (carsIds.get(i) != null && carsIds.get(i)) {
                addCarsList.add(carsList.get(i).getId());
            }
        }
        return addCarsList;
    }

    @Override
    public String listTeams(Integer page, Model model) {
        TeamsSearchForm teamsSearchForm = (TeamsSearchForm) model.asMap().get("teamsSearchForm");
        Page<TeamViewModel> teamList;
        if (teamsSearchForm == null) {
            teamsSearchForm = new TeamsSearchForm();
            teamList = teamService.getTeams(PageRequest.of(page, pageSize));
            if (teamList.isEmpty() && page != 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            } else if (teamList.isEmpty() && page == 0) {
                model.addAttribute("teamsSearchError", "not found");
            }
        } else {
            teamList = teamService.getTeamsByFilter(PageRequest.of(page, pageSize), teamsSearchForm);
            if (teamList.isEmpty()) {
                model.addAttribute("teamsSearchError", "not found");
            }
        }
        model.addAttribute("teamList", teamList);
        model.addAttribute("pageCount", Math.ceil((double) teamService.countTeams() / pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("TeamsSearchForm", teamsSearchForm);
        return "team/team-list";
    }

    @Override
    public String listTeamsSearch(TeamsSearchForm form, BindingResult bindingResult, Model
            model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("teamsSearchForm", form);
        return "redirect:/teams";
    }

    @Override
    public String details(String id, Model model) {
        TeamDetailsViewModel detailsViewModel = teamService.getTeam(id);
        if (detailsViewModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        model.addAttribute("teamDetails", detailsViewModel);
        return "team/team-details";
    }

    @Override
    public String createForm(Model model) {
        TeamCreateForm teamCreateForm = (TeamCreateForm) model.asMap().get("teamCreateForm");
        if (teamCreateForm == null) {
            teamCreateForm = new TeamCreateForm();
        }
        List<DriverSmallViewModel> driversList = driverService.getDriversSmall();
        List<Boolean> driverIds = getBooleansDrivers(teamCreateForm.getDriverIds(), driversList);
        teamCreateForm.setDriverIds(driverIds);
        model.addAttribute("driversList", driversList);

        List<CarSmallViewModel> carsList = carService.getCarsSmall();
        List<Boolean> carsIds = getBooleansCars(teamCreateForm.getCarIds(), carsList);
        teamCreateForm.setCarIds(carsIds);
        model.addAttribute("carsList", carsList);

        model.addAttribute("teamCreateForm", teamCreateForm);
        return "team/team-create";
    }

    @Override
    public String create(TeamCreateForm teamCreateForm, BindingResult bindingResult, Model
            model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("teamCreateForm", teamCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.teamCreateForm", bindingResult);
            return "redirect:/teams/create";
        }
        teamService.createTeam(teamCreateForm,
                getDriversByBooleansDrivers(teamCreateForm.getDriverIds(), driverService.getDriversSmall()),
                getDriversByBooleansCars(teamCreateForm.getCarIds(), carService.getCarsSmall())
        );
        return "redirect:/teams";
    }

    @Override
    public String editForm(String id, Model model) {
        TeamCreateForm teamEditForm = (TeamCreateForm) model.asMap().get("teamEditForm");
        if (teamEditForm == null) {
            teamEditForm = teamService.getEditTeam(id);
            if (teamEditForm == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
        model.addAttribute("teamEditForm", teamEditForm);
        model.addAttribute("teamEditId", id);
        return "team/team-edit";
    }

    @Override
    public String edit(String id, TeamCreateForm teamEditForm, BindingResult bindingResult, Model
            model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("teamEditForm", teamEditForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.teamEditForm", bindingResult);
            model.addAttribute("teamEditId", id);
            return "redirect:/teams/" + id + "/edit";
        }
        teamService.editTeam(id, teamEditForm);
        return "redirect:/teams/" + id;
    }

    @Override
    public String delete(String id) {
        teamService.deleteTeam(id);
        return "redirect:/teams";
    }

}
