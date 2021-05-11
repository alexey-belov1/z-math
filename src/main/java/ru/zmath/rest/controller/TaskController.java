package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.controller.alert.HeaderUtil;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.service.TaskService;
import ru.zmath.rest.service.UserService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final String entity = "task";

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(final TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<Task> findAll() {
        return this.taskService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Task> create(@RequestBody Task task) throws URISyntaxException {
        return new ResponseEntity<>(
            this.taskService.save(task),
            HeaderUtil.createSuccessAlert(entity),
            HttpStatus.CREATED
        );
    }
}
