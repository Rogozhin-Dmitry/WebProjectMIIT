package ru.rogozhinda.controllers;

import jakarta.validation.Valid;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rogozhinda.dto.user.UserRegistrationDto;

import java.security.Principal;

@RequestMapping("/users")
public interface AuthController {

    @GetMapping("/register")
    String register();

    @PostMapping("/register")
    String doRegister(@Valid UserRegistrationDto userRegistrationDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes);

    @GetMapping("/login")
    String login();

    @PostMapping("/login-error")
    String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes);

    @GetMapping("/profile")
    String profile(Principal principal, Model model);
}
