package ru.zmath.rest.service.mapper;

import org.mapstruct.*;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.service.dto.TaskDTO;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class,
    SubjectMapper.class,
    MethodMapper.class
})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.name", target = "subjectName")
    @Mapping(source = "method.id", target = "methodId")
    @Mapping(source = "method.name", target = "methodName")
    TaskDTO toDto(Task task);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "methodId", target = "method")
    Task toEntity(TaskDTO taskDTO);

    default Task fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }
}
