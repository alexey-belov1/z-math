package ru.zmath.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.zmath.back.model.Status;
import ru.zmath.back.model.Task;
import ru.zmath.back.repository.TaskRepository;
import ru.zmath.back.service.mapper.TaskMapper;
import ru.zmath.back.service.dto.TaskDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final AttachedFileService attachedFileService;
    private final PaymentService paymentService;
    private final TaskMapper taskMapper;

    public TaskService(final TaskRepository taskRepository, UserService userService, AttachedFileService attachedFileService, PaymentService paymentService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.attachedFileService = attachedFileService;
        this.paymentService = paymentService;
        this.taskMapper = taskMapper;
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream()
            .map(taskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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

    @Transactional
    public boolean setPayment(TaskDTO taskDTO) {
        Optional<Task> optional = this.taskRepository.findById(taskDTO.getId());
        if (optional.isPresent()
            && optional.get().getPayment() == null
            && taskDTO.getPayment().getId() == null) {
            taskDTO.setPayment(
                this.paymentService.save(taskDTO.getPayment())
            );
            this.taskRepository.save(this.taskMapper.toEntity(taskDTO));
            return true;
        }
        return false;
    }
}
