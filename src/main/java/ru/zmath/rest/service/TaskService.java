package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.model.Status;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.repository.TaskRepository;

import java.util.GregorianCalendar;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(final TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Task save(Task task) {
        task.setStatus(new Status(1));
        task.setCreated(GregorianCalendar.getInstance());
        task.setUser(userService.getCurrentUser().orElseThrow(
            () -> new RuntimeException("User not found in context")
        ));

        return this.taskRepository.save(task);
    }
}
