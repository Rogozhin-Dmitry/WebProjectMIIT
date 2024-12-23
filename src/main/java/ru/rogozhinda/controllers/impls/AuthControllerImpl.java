package ru.rogozhinda.controllers.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.controllers.AuthController;
import ru.rogozhinda.dto.user.UserProfileView;
import ru.rogozhinda.dto.user.UserRegistrationDto;
import ru.rogozhinda.entities.User;
import ru.rogozhinda.services.AuthService;

import java.security.Principal;

@Controller
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Autowired  // Типа убрать у всех конструкторов
    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @Override
    public String register() {
        return "auth/register";
    }

    @Override
    public String doRegister(UserRegistrationDto userRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:/users/register";
        }

        try {
            this.authService.register(userRegistrationDto);
        } catch (RuntimeException e) {
            switch (e.getMessage()) {
                case "passwords.match" ->
                        bindingResult.addError(new FieldError("userRegistrationDto", "password", "Пароли не совпадают"));
                case "email.used" ->
                        bindingResult.addError(new FieldError("userRegistrationDto", "email", "Email занят другим аккаунтом"));
                default -> throw e;
            }
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

    @Override
    public String login() {
        return "auth/login";
    }

    @Override
    public String onFailedLogin(String username,
                                RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @Override
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = authService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                username,
                user.getEmail(),
                user.getFullName(),
                user.getAge()
        );

        model.addAttribute("user", userProfileView);

        return "auth/profile";
    }
}
