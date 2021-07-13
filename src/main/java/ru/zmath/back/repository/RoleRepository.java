package ru.zmath.back.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zmath.back.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
