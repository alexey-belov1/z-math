package ru.zmath.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zmath.rest.controller.util.HeaderUtil;
import ru.zmath.rest.controller.util.PaginationUtil;
import ru.zmath.rest.model.Task;
import ru.zmath.rest.service.TaskService;
import ru.zmath.rest.service.UserService;

import java.net.URISyntaxException;
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

    /*@GetMapping("/")
    public List<Task> findAll() {
        return this.taskService.findAll();
    }*/

    @PostMapping("/")
    public ResponseEntity<Task> create(@RequestBody Task task) throws URISyntaxException {
        return new ResponseEntity<>(
            this.taskService.save(task),
            HeaderUtil.createSuccessAlert(entity),
            HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> findAll(Pageable pageable) {
        Page<Task> page = taskService.findByCriteria(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
