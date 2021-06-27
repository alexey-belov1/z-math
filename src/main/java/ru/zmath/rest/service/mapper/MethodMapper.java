package ru.zmath.rest.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.rest.model.Method;
import ru.zmath.rest.service.dto.MethodDTO;

@Mapper(componentModel = "spring")
public interface MethodMapper extends EntityMapper<MethodDTO, Method> {

    default Method fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Method method = new Method();
        method.setId(id);
        return method;
    }
}
