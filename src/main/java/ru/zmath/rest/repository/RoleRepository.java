package ru.zmath.rest.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.rest.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}