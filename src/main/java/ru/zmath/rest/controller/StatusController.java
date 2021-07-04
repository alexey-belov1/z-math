package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.service.StatusService;
import ru.zmath.rest.service.dto.StatusDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status")
    public ResponseEntity<List<StatusDTO>> findAll() {
        return new ResponseEntity<>(
            this.statusService.findAll(),
            HttpStatus.OK
        );
    }
}
