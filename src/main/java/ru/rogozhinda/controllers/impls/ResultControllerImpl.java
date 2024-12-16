package ru.rogozhinda.controllers.impls;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ResultService resultService;

    @Override
    public String details(String id, Model model) {
        ResultDetailsViewModel detailsViewModel = resultService.getResult(id);
        if (detailsViewModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        model.addAttribute("resultDetails", detailsViewModel);
        return "result/result-details";
    }

    @Override
    public String createForm(Model model) {
        ResultCreateForm resultCreateForm = (ResultCreateForm) model.asMap().get("resultCreateForm");
        if (resultCreateForm == null) {
            resultCreateForm = new ResultCreateForm();
        }
        model.addAttribute("resultCreateForm", resultCreateForm);
        return "result/result-create";
    }

    @Override
    public String create(ResultCreateForm resultCreateForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("resultCreateForm", resultCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resultCreateForm", bindingResult);
            return "redirect:/results/create";
        }
        resultService.createResult(resultCreateForm);
        return "redirect:/results";  // TODO фикс возврата к объекту
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
        return "redirect:/results/" + id;
    }

    @Override
    public String delete(String id) {
        resultService.deleteResult(id);
        return "redirect:/results"; // TODO фикс возврата к объекту
    }

}
