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
import ru.rogozhinda.controllers.ResultController;
import ru.rogozhinda.dto.result.ResultCreateForm;
import ru.rogozhinda.dto.result.ResultDetailsViewModel;
import ru.rogozhinda.services.ResultService;

@Controller
public class ResultControllerImpl implements ResultController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final ResultService resultService;

    public ResultControllerImpl(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public String details(String id, Model model) {
        ResultDetailsViewModel detailsViewModel = resultService.getResult(id);
        if (detailsViewModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        LOG.log(Level.INFO, "getResultList");
        model.addAttribute("resultDetails", detailsViewModel);
        return "result/result-details";
    }

    @Override
    public String createForm(Model model, String raceTeamId, String raceId) {
        ResultCreateForm resultCreateForm = (ResultCreateForm) model.asMap().get("resultCreateForm");
        if (resultCreateForm == null) {
            resultCreateForm = new ResultCreateForm();
        }
        LOG.log(Level.INFO, "GetResultCreate");
        model.addAttribute("resultCreateForm", resultCreateForm);
        model.addAttribute("raceTeamId", raceTeamId);
        model.addAttribute("raceId", raceId);
        return "result/result-create";
    }

    @Override
    public String create(ResultCreateForm resultCreateForm, BindingResult bindingResult, Model model, String raceTeamId, String raceId, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("resultCreateForm", resultCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resultCreateForm", bindingResult);
            return "redirect:/results/create?raceTeamId=" + raceTeamId + "&raceId=" + raceId;
        }
        LOG.log(Level.INFO, "PostResultCreate");
        resultService.createResult(resultCreateForm, raceTeamId);
        return "redirect:/races/" + raceId;
    }

    @Override
    public String editForm(String id, Model model) {
        ResultCreateForm resultEditForm = (ResultCreateForm) model.asMap().get("resultEditForm");
        if (resultEditForm == null) {
            resultEditForm = resultService.getEditResult(id);
            if (resultEditForm == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
        LOG.log(Level.INFO, "GetResultEdit");
        model.addAttribute("resultEditForm", resultEditForm);
        model.addAttribute("resultEditId", id);
        return "result/result-edit";
    }

    @Override
    public String edit(String id, ResultCreateForm resultEditForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("resultEditForm", resultEditForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resultEditForm", bindingResult);
            model.addAttribute("resultEditId", id);
            return "redirect:/results/" + id + "/edit";
        }
        resultService.editResult(id, resultEditForm);
        LOG.log(Level.INFO, "PostResultEdit" + id);
        return "redirect:/results/" + id;
    }

    @Override
    public String delete(String id) {
        String raceId = resultService.getResultRaceId(id);
        resultService.deleteResult(id);
        LOG.log(Level.INFO, "GetResultDelete" + id);
        return "redirect:/races/" + raceId;
    }

}
