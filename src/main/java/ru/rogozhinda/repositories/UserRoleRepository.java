package ru.rogozhinda.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rogozhinda.entities.Role;
import ru.rogozhinda.entities.enums.UserRoles;


import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findRoleByName(UserRoles role);
}
