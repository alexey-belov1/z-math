package ru.zmath.back.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zmath.back.model.AttachedFile;
import ru.zmath.back.service.dto.AttachedFileDTO;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface AttachedFileMapper extends EntityMapper<AttachedFileDTO, AttachedFile> {

    @Mapping(source = "task.id", target = "taskId")
    AttachedFileDTO toDto(AttachedFile attachedFile);

    @Mapping(source = "taskId", target = "task")
    AttachedFile toEntity(AttachedFileDTO attachedFile);

    default AttachedFile fromId(Integer id) {
        if (id == null) {
            return null;
        }
        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setId(id);
        return attachedFile;
    }
}
