package ru.zmath.back.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.back.model.Method;
import ru.zmath.back.service.dto.MethodDTO;

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
