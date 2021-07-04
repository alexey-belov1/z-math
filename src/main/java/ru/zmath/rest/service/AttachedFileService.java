package ru.zmath.rest.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.zmath.rest.model.AttachedFile;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.repository.AttachedFileRepository;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.createDirectories;
import static java.nio.file.Paths.get;

@Service
public class AttachedFileService {

    @Value("${file-path}")
    private String filePath;

    private final AttachedFileRepository attachedFileRepository;

    public AttachedFileService(AttachedFileRepository attachedFileRepository) {
        this.attachedFileRepository = attachedFileRepository;
    }

    @Transactional(readOnly = true)
    public Optional<AttachedFile> findById(int id) {
        return this.attachedFileRepository.findById(id);
    }

    public AttachedFile createAndSave(MultipartFile file, int taskId, String type) {
        String dir = save(file, taskId, type);

        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setName(file.getOriginalFilename());
        attachedFile.setSize(String.valueOf(file.getSize()));
        attachedFile.setExtension(file.getContentType());
        attachedFile.setPath(dir + File.separator + file.getOriginalFilename());
        attachedFile.setType(type);
        attachedFile.setTask(new Task(taskId));
        return  attachedFile;
    }

    private String save(MultipartFile file, int taskId, String type) {
        try(InputStream is = file.getInputStream()) {
            Path dir = createDirectories(get(filePath, String.valueOf(taskId), type));
            String name = file.getOriginalFilename();
            Files.copy(is, dir.resolve(Objects.requireNonNull(name)));
            return dir.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean delete(AttachedFile attachedFile) {
        return new File(attachedFile.getPath()).delete();
    }

    @Transactional(readOnly = true)
    public Resource download(int id) {
        Optional<AttachedFile> optional = this.attachedFileRepository.findById(id);
        if (optional.isPresent()) {
            AttachedFile attachedFile = optional.get();
            try {
                Path path = Paths.get(attachedFile.getPath());
                return new UrlResource(path.toUri());
            } catch (MalformedURLException e) {
                throw new RuntimeException("File download error");
            }
        }
        return null;
    }
}
