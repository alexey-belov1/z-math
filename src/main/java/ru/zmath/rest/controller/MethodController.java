package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.service.MethodService;
import ru.zmath.rest.service.dto.MethodDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MethodController {

    private final MethodService methodService;

    public MethodController(final MethodService methodService) {
        this.methodService = methodService;
    }

    @GetMapping("/method")
    public ResponseEntity<List<MethodDTO>> findAll() {
        return new ResponseEntity<>(
            this.methodService.findAll(),
            HttpStatus.OK
        );
    }
}
