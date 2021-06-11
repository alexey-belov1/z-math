package ru.zmath.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.zmath.rest.model.AttachedFile;
import ru.zmath.rest.model.Status;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.repository.TaskRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
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

    @Transactional(readOnly = true)
    public Optional<Task> findById(int id) {
        return this.taskRepository.findById(id);
    }

    @Transactional
    public Task save(Task task, List<MultipartFile> files) {
        task.setStatus(new Status(1));
        task.setCreated(GregorianCalendar.getInstance());
        task.setUser(userService.getCurrentUser().orElseThrow(
            () -> new RuntimeException("User not found in context")
        ));
        this.taskRepository.save(task);
        task.setAttachedFile(
            files.stream()
                .map(file -> createAttachedFile(file, task))
                .collect(Collectors.toList())
        );
        return task;
    }

    @Transactional
    public Task update(Task task, List<MultipartFile> files) {
        //Временная заглушка
        task.getAttachedFile()
            .forEach(attachedFile -> attachedFile.setTask(task));

        files.forEach(file ->
            task.getAttachedFile().add(
                createAttachedFile(file, task)
            )
        );

        return this.taskRepository.save(task);
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

    private AttachedFile createAttachedFile(MultipartFile file, Task task) {
        String dir = saveFile(file, task.getId());

        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setName(file.getOriginalFilename());
        attachedFile.setSize(String.valueOf(file.getSize()));
        attachedFile.setExtension(file.getContentType());
        attachedFile.setPath(dir + File.separator + file.getOriginalFilename());
        attachedFile.setTask(task);
        return  attachedFile;
    }

    private String saveFile(MultipartFile file, int taskId) {
        try(InputStream is = file.getInputStream()) {
            String dateFormat = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            Path dir = createDirectories(get(filePath, dateFormat, String.valueOf(taskId)));
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
