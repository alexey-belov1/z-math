package ru.zmath.rest.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.rest.model.Role;
import ru.zmath.rest.service.dto.RoleDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    default Role fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Role role = new Role();
        role.setId(id);
        return role;
    }
}
