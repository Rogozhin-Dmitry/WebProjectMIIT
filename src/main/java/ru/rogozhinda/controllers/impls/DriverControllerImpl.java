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
import ru.rogozhinda.controllers.DriverController;
import ru.rogozhinda.dto.driver.DriverCreateForm;
import ru.rogozhinda.dto.driver.DriverDetailsViewModel;
import ru.rogozhinda.dto.driver.DriverViewModel;
import ru.rogozhinda.dto.driver.DriversSearchForm;
import ru.rogozhinda.services.DriverService;

@Controller
public class DriverControllerImpl implements DriverController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private static final int pageSize = 5;
    private final DriverService driverService;

    public DriverControllerImpl(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String listDrivers(Integer page, Model model) {
        DriversSearchForm driversSearchForm = (DriversSearchForm) model.asMap().get("driversSearchForm");
        Page<DriverViewModel> driverList;
        if (driversSearchForm == null) {
            driversSearchForm = new DriversSearchForm();
            driverList = driverService.getDrivers(PageRequest.of(page, pageSize));
            if (driverList.isEmpty() && page != 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            } else if (driverList.isEmpty() && page == 0) {
                model.addAttribute("driversSearchError", "not found");
            }
        } else {
            driverList = driverService.getDriversByFilter(PageRequest.of(page, pageSize), driversSearchForm);
            if (driverList.isEmpty()) {
                model.addAttribute("driversSearchError", "not found");
            }
        }
        LOG.log(Level.INFO, "getDriverList");
        model.addAttribute("driverList", driverList);
        model.addAttribute("pageCount", Math.ceil((double) driverService.countDrivers() / pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("DriversSearchForm", driversSearchForm);
        return "driver/driver-list";
    }

    @Override
    public String listDriversSearch(DriversSearchForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("driversSearchForm", form);
        return "redirect:/drivers";
    }

    @Override
    public String details(String id, Model model) {
        DriverDetailsViewModel detailsViewModel = driverService.getDriver(id);
        if (detailsViewModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        LOG.log(Level.INFO, "getDriverDetails " + id);
        model.addAttribute("driverDetails", detailsViewModel);
        return "driver/driver-details";
    }

    @Override
    public String createForm(Model model) {
        DriverCreateForm driverCreateForm = (DriverCreateForm) model.asMap().get("driverCreateForm");
        if (driverCreateForm == null) {
            driverCreateForm = new DriverCreateForm();
        }
        LOG.log(Level.INFO, "GetDriverCreate");
        model.addAttribute("driverCreateForm", driverCreateForm);
        return "driver/driver-create";
    }

    @Override
    public String create(DriverCreateForm driverCreateForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("driverCreateForm", driverCreateForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.driverCreateForm", bindingResult);
            return "redirect:/drivers/create";
        }
        LOG.log(Level.INFO, "PostDriverCreate");
        driverService.createDriver(driverCreateForm);
        return "redirect:/drivers";
    }

    @Override
    public String editForm(String id, Model model) {
        DriverCreateForm driverEditForm = (DriverCreateForm) model.asMap().get("driverEditForm");
        if (driverEditForm == null) {
            driverEditForm = driverService.getEditDriver(id);
            if (driverEditForm == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
        LOG.log(Level.INFO, "GetDriverEdit");
        model.addAttribute("driverEditForm", driverEditForm);
        model.addAttribute("driverEditId", id);
        return "driver/driver-edit";
    }

    @Override
    public String edit(String id, DriverCreateForm driverEditForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("driverEditForm", driverEditForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.driverEditForm", bindingResult);
            model.addAttribute("driverEditId", id);
            return "redirect:/drivers/" + id + "/edit";
        }
        LOG.log(Level.INFO, "PostDriverEdit" + id);
        driverService.editDriver(id, driverEditForm);
        return "redirect:/drivers/" + id;
    }

    @Override
    public String delete(String id) {
        driverService.deleteDriver(id);
        LOG.log(Level.INFO, "GetDriverDelete" + id);
        return "redirect:/drivers";
    }

}
