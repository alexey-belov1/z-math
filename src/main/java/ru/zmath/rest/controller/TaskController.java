package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.model.Status;
import ru.zmath.rest.model.Subject;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.model.User;
import ru.zmath.rest.service.TaskService;

import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> findAll() {
        return this.taskService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Status status = new Status();
        status.setId(1);

        task.setStatus(status);
        task.setCreated(GregorianCalendar.getInstance());

        return new ResponseEntity<Task>(
                this.taskService.save(task),
                HttpStatus.CREATED
        );
    }
}