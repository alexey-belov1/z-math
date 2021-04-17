package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Task save(Task task) {
        return this.taskRepository.save(task);
    }
}
