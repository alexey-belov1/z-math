package ru.zmath.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.zmath.rest.model.*;
import ru.zmath.rest.repository.TaskRepository;
import ru.zmath.rest.service.dto.TaskDTO;
import ru.zmath.rest.service.mapper.TaskMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.createDirectories;
import static java.nio.file.Paths.get;

@Service
public class TaskService {
    @Value("${file-path}")
    private String filePath;

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
        task.setAttachedFile(
            files.stream()
                .map(file -> createAttachedFile(file, task, "task"))
                .collect(Collectors.toList())
        );
        return this.taskMapper.toDto(task);
    }

    @Transactional
    public TaskDTO update(TaskDTO taskDTO, List<MultipartFile> files) {
        Task task = this.taskMapper.toEntity(taskDTO);
        files.forEach(file ->
            task.getAttachedFile().add(
                createAttachedFile(file, task, "solve")
            )
        );
        return this.taskMapper.toDto(this.taskRepository.save(task));
    }

    @Transactional
    public boolean delete(int id) {
        Optional<Task> optional = this.taskRepository.findById(id);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.getAttachedFile().forEach(this::deleteFile);
            this.taskRepository.delete(task);
            return true;
        }
        return false;
    }

    private AttachedFile createAttachedFile(MultipartFile file, Task task, String type) {
        String dir = saveFile(file, task.getId(), type);

        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setName(file.getOriginalFilename());
        attachedFile.setSize(String.valueOf(file.getSize()));
        attachedFile.setExtension(file.getContentType());
        attachedFile.setPath(dir + File.separator + file.getOriginalFilename());
        attachedFile.setType(type);
        attachedFile.setTask(task);
        return  attachedFile;
    }

    private String saveFile(MultipartFile file, int taskId, String type) {
        try(InputStream is = file.getInputStream()) {
            Path dir = createDirectories(get(filePath, String.valueOf(taskId), type));
            String name = file.getOriginalFilename();
            Files.copy(is, dir.resolve(Objects.requireNonNull(name)));
            return dir.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean deleteFile(AttachedFile attachedFile) {
        return new File(attachedFile.getPath()).delete();
    }


/*        //Поиск файлов, которые нужно удалить и их удаление
        taskRepository.findById(task.getId()).ifPresent(taskOld -> {
            List<AttachedFile> attachedFiles = taskOld.getAttachedFile();
            task.getAttachedFile().forEach(attachedFile -> {
                attachedFiles.remove(attachedFile);
            });
            attachedFiles.forEach(this::deleteFile);
        });*/
}
