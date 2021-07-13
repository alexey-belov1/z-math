package ru.zmath.back.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.back.model.Role;
import ru.zmath.back.service.dto.RoleDTO;

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
