package ru.zmath.rest.controller;

import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.service.SubjectService;
import ru.zmath.rest.service.dto.SubjectDTO;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(final SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    public List<SubjectDTO> findAll() {
        return this.subjectService.findAll();
    }
}
