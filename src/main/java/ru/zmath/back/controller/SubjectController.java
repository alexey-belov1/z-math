package ru.zmath.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.back.service.dto.SubjectDTO;
import ru.zmath.back.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subject")
    public ResponseEntity<List<SubjectDTO>> findAll() {
        return new ResponseEntity<>(
            this.subjectService.findAll(),
            HttpStatus.OK
        );
    }
}
