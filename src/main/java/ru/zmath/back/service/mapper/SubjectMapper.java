package ru.zmath.back.service.mapper;

import org.mapstruct.Mapper;
import ru.zmath.back.model.Subject;
import ru.zmath.back.service.dto.SubjectDTO;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {

    default Subject fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
