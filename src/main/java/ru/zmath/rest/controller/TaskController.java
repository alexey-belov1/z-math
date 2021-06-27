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
import ru.zmath.rest.model.Task;
import ru.zmath.rest.service.query.TaskQueryService;
import ru.zmath.rest.service.TaskService;
import ru.zmath.rest.service.UserService;
import ru.zmath.rest.service.criteria.TaskCriteria;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final String entity = "task";

    private final TaskService taskService;
    private final TaskQueryService taskQueryService;
    private final UserService userService;

    public TaskController(final TaskService taskService, TaskQueryService taskQueryService, UserService userService) {
        this.taskService = taskService;
        this.taskQueryService = taskQueryService;
        this.userService = userService;
    }

    /*@GetMapping("/")
    public List<Task> findAll() {
        return this.taskService.findAll();
    }*/

/*    @PostMapping("/")
    public ResponseEntity<Task> create(@RequestBody Task task) throws URISyntaxException {
        return new ResponseEntity<>(
            this.taskService.save(task),
            HeaderUtil.createSuccessAlert(entity),
            HttpStatus.CREATED
        );
    }*/


    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<Task> create(@RequestPart("task") Task task,
                                       @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) throws URISyntaxException {
        return new ResponseEntity<>(
            this.taskService.save(task, files),
            HeaderUtil.createSuccessAlert(entity),
            HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<Task> update(@RequestPart("task") Task task,
                                       @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) throws URISyntaxException {
        return new ResponseEntity<>(
            this.taskService.update(task, files),
            HeaderUtil.createSuccessAlert(entity),
            HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) throws URISyntaxException {
        return new ResponseEntity<>(
            this.taskService.delete(id),
            HeaderUtil.createSuccessAlert(entity),
            HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> findAll(TaskCriteria criteria, Pageable pageable) {
        Page<Task> page = this.taskQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable int id) {
        Optional<Task> res = this.taskService.findById(id);
        return new ResponseEntity<>(
            res.orElseGet(Task::new),
            res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
}
