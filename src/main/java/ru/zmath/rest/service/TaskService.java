package ru.zmath.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.zmath.rest.model.*;
import ru.zmath.rest.repository.TaskRepository;
import ru.zmath.rest.service.dto.TaskDTO;
import ru.zmath.rest.service.mapper.TaskMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final AttachedFileService attachedFileService;
    private final TaskMapper taskMapper;

    public TaskService(final TaskRepository taskRepository, UserService userService, AttachedFileService attachedFileService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.attachedFileService = attachedFileService;
        this.taskMapper = taskMapper;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<TaskDTO> findById(int id) {
        return this.taskRepository.findById(id)
            .map(this.taskMapper::toDto);
    }

    @Transactional
    public TaskDTO save(TaskDTO taskDTO, List<MultipartFile> files) {
        Task task = this.taskMapper.toEntity(taskDTO);
        task.setStatus(new Status(1));
        task.setCreated(GregorianCalendar.getInstance());
        task.setUser(userService.getCurrentUser().orElseThrow(
            () -> new RuntimeException("User not found in context")
        ));
        this.taskRepository.save(task);
        task.setAttachedFiles(
            files.stream()
                .map(file -> attachedFileService.createAndSave(file, task.getId(), "task"))
                .collect(Collectors.toList())
        );
        return this.taskMapper.toDto(task);
    }

    @Transactional
    public boolean update(TaskDTO taskDTO, List<MultipartFile> files) {
        if (this.taskRepository.findById(taskDTO.getId()).isPresent()) {
            Task task = this.taskMapper.toEntity(taskDTO);
            files.forEach(file ->
                task.getAttachedFiles().add(
                    attachedFileService.createAndSave(file, task.getId(), "solve")
                )
            );
            this.taskRepository.save(task);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean delete(int id) {
        Optional<Task> optional = this.taskRepository.findById(id);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.getAttachedFiles().forEach(attachedFileService::delete);
            this.taskRepository.delete(task);
            return true;
        }
        return false;
    }
}
