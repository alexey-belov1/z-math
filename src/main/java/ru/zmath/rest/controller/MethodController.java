package ru.zmath.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.service.MethodService;
import ru.zmath.rest.service.dto.MethodDTO;

import java.util.List;

@RestController
@RequestMapping("/method")
public class MethodController {

    private final MethodService methodService;

    public MethodController(final MethodService methodService) {
        this.methodService = methodService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MethodDTO>> findAll() {
        return ResponseEntity.ok().body(methodService.findAll());
    }
}
