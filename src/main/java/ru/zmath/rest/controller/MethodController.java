package ru.zmath.rest.controller;

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
    public List<MethodDTO> findAll() {
        return this.methodService.findAll();
    }
}
