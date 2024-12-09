package ru.rogozhinda.services.impls;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rogozhinda.dto.user.UserRegistrationDto;
import ru.rogozhinda.entities.User;
import ru.rogozhinda.entities.enums.UserRoles;
import ru.rogozhinda.repositories.UserRepository;
import ru.rogozhinda.repositories.UserRoleRepository;
import ru.rogozhinda.services.AuthService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void register(UserRegistrationDto registrationDTO) {
        if (!registrationDTO.password().equals(registrationDTO.confirmPassword())) {
            throw new RuntimeException("passwords.match");  // TODO добавить красивые эксепшены
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.email());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.
                findRoleByName(UserRoles.USER).orElseThrow();

        User user = new User(
                registrationDTO.username(),
                passwordEncoder.encode(registrationDTO.password()),
                registrationDTO.email(),
                registrationDTO.fullname(),
                registrationDTO.age()
        );

        user.setRoles(List.of(userRole));

        this.userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
