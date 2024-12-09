package ru.rogozhinda.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AppUserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
