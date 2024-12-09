package ru.rogozhinda.services;

import ru.rogozhinda.dto.user.UserRegistrationDto;
import ru.rogozhinda.entities.User;

public interface AuthService {
    void register(UserRegistrationDto registrationDTO);

    User getUser(String username);
}
