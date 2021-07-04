package ru.zmath.rest.service.mapper;

import org.mapstruct.*;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.service.dto.TaskDTO;

@Mapper(componentModel = "spring", uses = {
    UserMapper.class,
    SubjectMapper.class,
    StatusMapper.class,
    PaymentMapper.class,
    AttachedFileMapper.class
})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    TaskDTO toDto(Task task);

    @Mapping(source = "userId", target = "user")
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
