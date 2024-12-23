package ru.rogozhinda.controllers.impls;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rogozhinda.controllers.HomeController;

@Controller
public class HomeControllerImpl  implements HomeController {

    @Override
    public String index(Model model) {
        return "base/index";
    }
}
