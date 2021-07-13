package ru.zmath.back.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.back.model.User;
import ru.zmath.back.service.dto.UserDTO;

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
