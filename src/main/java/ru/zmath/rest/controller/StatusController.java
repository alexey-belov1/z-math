package ru.zmath.rest.controller;

import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.service.StatusService;
import ru.zmath.rest.service.dto.StatusDTO;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/")
    public List<StatusDTO> findAll() {
        return this.statusService.findAll();
    }
}
