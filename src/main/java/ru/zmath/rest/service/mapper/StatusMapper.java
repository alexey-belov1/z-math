package ru.zmath.rest.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.rest.model.Status;
import ru.zmath.rest.service.dto.StatusDTO;

@Mapper(componentModel = "spring")
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {

    default Status fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Status status = new Status();
        status.setId(id);
        return status;
    }
}
