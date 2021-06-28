package ru.zmath.rest.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.service.AttachedFileService;

@RestController
@RequestMapping("/api")
public class AttachedFileController {

    private final AttachedFileService attachedFileService;

    public AttachedFileController(AttachedFileService attachedFileService) {
        this.attachedFileService = attachedFileService;
    }

    @GetMapping("/attached-files/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id) {
        Resource file = this.attachedFileService.loadFile(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }
}
