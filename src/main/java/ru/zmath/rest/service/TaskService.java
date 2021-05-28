package ru.zmath.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.zmath.rest.model.Status;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.repository.TaskRepository;

import java.util.GregorianCalendar;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final AttachedFileService attachedFileService;

    public TaskService(final TaskRepository taskRepository, UserService userService, AttachedFileService attachedFileService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.attachedFileService = attachedFileService;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Task> findByCriteria(Pageable page) {
        return taskRepository.findAll(page);
    }

    @Transactional
    public Task save(Task task, List<MultipartFile> files) {
        task.setStatus(new Status(1));
        task.setCreated(GregorianCalendar.getInstance());
        task.setUser(userService.getCurrentUser().orElseThrow(
            () -> new RuntimeException("User not found in context")
        ));
        task = this.taskRepository.save(task);
        attachedFileService.processFile(files, null, task.getId());
        return this.taskRepository.save(task);
    }
}
