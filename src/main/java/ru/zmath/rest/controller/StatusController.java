package ru.zmath.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zmath.rest.controller.util.HeaderUtil;
import ru.zmath.rest.controller.util.PaginationUtil;
import ru.zmath.rest.model.Status;
import ru.zmath.rest.model.Subject;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.service.StatusService;
import ru.zmath.rest.service.criteria.TaskCriteria;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final String entity = "status";

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/")
    public List<Status> findAll() {
        return this.statusService.findAll();
    }
}
