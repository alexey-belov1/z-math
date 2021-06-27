package ru.zmath.rest.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.rest.model.User;
import ru.zmath.rest.service.dto.UserDTO;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    default User fromId(Integer id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
