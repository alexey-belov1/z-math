package ru.zmath.rest.service;

import java.io.File;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.nio.file.Files.createDirectories;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

@Service
public class AttachedFileService {

    @Value("${file-path}")
    private String filePath;

    private final AttachedFileRepository attachedFileRepository;

    public AttachedFileService(AttachedFileRepository attachedFileRepository) {
        this.attachedFileRepository = attachedFileRepository;
    }

/*
    public Optional<AttachedFile> findById(int id) {
        return this.attachedFileRepository.findById(id);
    }
*/

/*    *//**
     * Метод для обработки файла. Сохранения на диск и удаления
     *//*
    public void processFile(List<MultipartFile> files, List<Integer> deleteFileList, Task task) {
        if (files != null && files.size() > 0) {
            this.saveFiles(files, task);
        }
        if (deleteFileList!= null && deleteFileList.size() > 0) {
            this.deleteFiles(deleteFileList);
        }
    }

    *//**
     * Сохранение файлов на диск
     *//*
    private void saveFiles(List<MultipartFile> files, Task task) {
        List<AttachedFile> attachedFiles = new LinkedList<>();
        for (MultipartFile file : files) {
            AttachedFile attachedFile = this.createAttachedFile(file, task);
            if (attachedFile!= null) {
                attachedFiles.add(attachedFile);
            }
        }
        task.setAttachedFile(attachedFiles);
    }*/

    /**
     * удаление файла по id  и из реопзитория
     */
    private void deleteFiles(List<Integer> deletedId) {
        Iterable<AttachedFile> attachedFiles = attachedFileRepository.findAllById(deletedId);
        attachedFileRepository.deleteAll(attachedFiles);
        for (AttachedFile attachedFile : attachedFiles) {
            File file = new File(attachedFile.getPath());
            if (file.delete()) {
                //log.debug("Файл {} удален", attachedFile.getName());
            } else {
                //log.debug("Файл {} не удален", attachedFile.getName());
            }
        }
    }

    public Resource loadFile(int id) {
        AttachedFile attachedFile = this.attachedFileRepository.findById(id).orElse(null);
        if (attachedFile != null) {
            try {
                Path file = Paths.get(attachedFile.getPath());
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("FAIL!");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("FAIL!");
            }
        }
        return null;
    }

/*    private AttachedFile createAttachedFile(MultipartFile file, Task task) {
        String name = RandomStringUtils.randomAlphanumeric(20) + file.getOriginalFilename();
        Path dir = null;
        try {
            dir = createDirectories(get(requireNonNull(filePath), getCurrentDate(), String.valueOf(task.getId())));
        } catch (IOException e) {
            //log.error(e.getMessage(), e);
        }
        if (dir != null) {
            try (InputStream is = file.getInputStream()) {
                Files.copy(is, dir.resolve(name));
                AttachedFile attachedFile = new AttachedFile();
                attachedFile.setName(file.getOriginalFilename());
                attachedFile.setSize(String.valueOf(file.getSize()));
                attachedFile.setExtension(file.getContentType());
                attachedFile.setPath(dir.toString() + File.separator + name);
                attachedFile.setTask(task);
                return  attachedFile;
            } catch (IOException e) {
                //log.error(e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(new Date());
    }*/


}
