package ru.rogozhinda.controllers.impls;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.controllers.RaceController;
import ru.rogozhinda.dto.race.RaceCreateForm;
import ru.rogozhinda.dto.race.RaceDetailsViewModel;
import ru.rogozhinda.dto.race.RaceViewModel;
import ru.rogozhinda.dto.race.RacesSearchForm;
import ru.rogozhinda.services.RaceService;

@Controller
public class RaceControllerImpl implements RaceController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private static final int pageSize = 5;
    private final RaceService raceService;

    public RaceControllerImpl(RaceService raceService) {
        this.raceService = raceService;
    }

    @Override
    public String listRaces(Integer page, Model model) {
        RacesSearchForm racesSearchForm = (RacesSearchForm) model.asMap().get("racesSearchForm");
        Page<RaceViewModel> raceList;
        if (racesSearchForm == null) {
            racesSearchForm = new RacesSearchForm();
            raceList = raceService.getRaces(PageRequest.of(page, pageSize));
            if (raceList.isEmpty() && page != 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            } else if (raceList.isEmpty() && page == 0) {
                model.addAttribute("racesSearchError", "not found");
            }
        } else {
            raceList = raceService.getRacesByFilter(PageRequest.of(page, pageSize), racesSearchForm);
            if (raceList.isEmpty()) {
                model.addAttribute("racesSearchError", "not found");
            }
        }
        LOG.log(Level.INFO, "getСarList");
        model.addAttribute("raceList", raceList);
        model.addAttribute("pageCount", Math.ceil((double) raceService.countRaces() / pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("RacesSearchForm", racesSearchForm);
        return "race/race-list";
    }

    @Override
    public String listRacesSearch(RacesSearchForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("racesSearchForm", form);
        return "redirect:/races";
    }

    @Override
    public String details(String id, Model model) {
        RaceDetailsViewModel detailsViewModel = raceService.getRace(id);
        if (detailsViewModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        LOG.log(Level.INFO, "getRaceDetails " + id);
        model.addAttribute("raceDetails", detailsViewModel);
        return "race/race-details";
    }

    @Override
    public String createForm(Model model) {
        RaceCreateForm raceCreateForm = (RaceCreateForm) model.asMap().get("raceCreateForm");
        if (raceCreateForm == null) {
            raceCreateForm = new RaceCreateForm();
        }
        LOG.log(Level.INFO, "GetRaceCreate");
        model.addAttribute("raceCreateForm", raceCreateForm);
        return "race/race-create";
    }

    @Override
    public String create(RaceCreateForm raceCreateForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("raceCreateForm", raceCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.raceCreateForm", bindingResult);
            return "redirect:/races/create";
        }
        LOG.log(Level.INFO, "PostRaceCreate");
        raceService.createRace(raceCreateForm);
        return "redirect:/races";
    }

    @Override
    public String editForm(String id, Model model) {
        RaceCreateForm raceEditForm = (RaceCreateForm) model.asMap().get("raceEditForm");
        if (raceEditForm == null) {
            raceEditForm = raceService.getEditRace(id);
            if (raceEditForm == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
        LOG.log(Level.INFO, "GetRaceEdit");
        model.addAttribute("raceEditForm", raceEditForm);
        model.addAttribute("raceEditId", id);
        return "race/race-edit";
    }

    @Override
    public String edit(String id, RaceCreateForm raceEditForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("raceEditForm", raceEditForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.raceEditForm", bindingResult);
            model.addAttribute("raceEditId", id);
            return "redirect:/races/" + id + "/edit";
        }
        LOG.log(Level.INFO, "PostRaceEdit" + id);
        raceService.editRace(id, raceEditForm);
        return "redirect:/races/" + id;
    }

    @Override
    public String delete(String id) {
        raceService.deleteRace(id);
        LOG.log(Level.INFO, "GetRaceDelete" + id);
        return "redirect:/races";
    }

}
