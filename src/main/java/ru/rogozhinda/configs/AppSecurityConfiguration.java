package ru.rogozhinda.configs;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.rogozhinda.entities.enums.UserRoles;
import ru.rogozhinda.repositories.UserRepository;
import ru.rogozhinda.services.impls.AppUserDetailsServiceImpl;


@Configuration
public class AppSecurityConfiguration {
    private final UserRepository userRepository;

    public AppSecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeHttpRequests ->
                                authorizeHttpRequests.
                                        requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                        .permitAll()
                                        .requestMatchers("/favicon.ico").permitAll()
                                        .requestMatchers("/error").permitAll()
                                        .requestMatchers("/users/login", "/users/register", "/users/login-error")
                                        .permitAll()
                                        .requestMatchers("/", "/cars", "/drivers", "/races", "/teams").permitAll().
                                        requestMatchers("/users/profile").authenticated().
                                        requestMatchers("/cars/{}", "/drivers/{}", "/races/{}", "/teams/{}", "/results/{}").authenticated().
                                        requestMatchers("/cars/{}/edit", "/drivers/{}/edit", "/races/{}/edit", "/teams/{}/edit",
                                                "/results/{}/edit", "/raceteams/{}/edit").hasAnyRole(UserRoles.MODERATOR.name(), UserRoles.ADMIN.name()).
                                        requestMatchers("/cars/create", "/drivers/create", "/races/create", "/teams/create",
                                                "/results/create", "/raceteams/create").hasAnyRole(UserRoles.MODERATOR.name(), UserRoles.ADMIN.name()).
                                        requestMatchers("/cars/{}/delete", "/drivers/{}/delete", "/races/{}/delete",
                                                "/teams/{}/delete", "/results/{}/delete", "/raceteams/{}/delete").hasRole(UserRoles.ADMIN.name()).
                                        anyRequest().authenticated()
                )
                .formLogin(
                        (formLogin) ->
                                formLogin.
                                        loginPage("/users/login").
                                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                                        defaultSuccessUrl("/").
                                        failureForwardUrl("/users/login-error")
                )
                .logout((logout) ->
                        logout.logoutUrl("/users/logout").
                                logoutSuccessUrl("/").
                                invalidateHttpSession(true)
                ).securityContext(
                        securityContext -> securityContext.
                                securityContextRepository(securityContextRepository)
                );

        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsServiceImpl(userRepository);
    }
}
