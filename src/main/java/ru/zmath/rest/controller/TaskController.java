package ru.zmath.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zmath.rest.controller.util.HeaderUtil;
import ru.zmath.rest.controller.util.PaginationUtil;
import ru.zmath.rest.service.dto.TaskDTO;
import ru.zmath.rest.service.query.TaskQueryService;
import ru.zmath.rest.service.TaskService;
import ru.zmath.rest.service.criteria.TaskCriteria;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;
    private final TaskQueryService taskQueryService;

    public TaskController(final TaskService taskService, TaskQueryService taskQueryService) {
        this.taskService = taskService;
        this.taskQueryService = taskQueryService;
    }

    @GetMapping("/task")
    public ResponseEntity<List<TaskDTO>> findByCriteria(TaskCriteria criteria, Pageable pageable) {
        Page<TaskDTO> page = this.taskQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable int id) {
        Optional<TaskDTO> res = this.taskService.findById(id);
        return new ResponseEntity<>(
            res.orElseGet(TaskDTO::new),
            res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping(value = "/task", consumes = {"multipart/form-data"})
    public ResponseEntity<TaskDTO> create(@RequestPart("taskDTO") TaskDTO taskDTO,
                                          @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        return new ResponseEntity<>(
            this.taskService.save(taskDTO, files),
            HeaderUtil.createSuccessAlert("taskCreate"),
            HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/task", consumes = {"multipart/form-data"})
    public ResponseEntity<TaskDTO> update(@RequestPart("taskDTO") TaskDTO taskDTO,
                                          @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        return new ResponseEntity<>(
            this.taskService.update(taskDTO, files),
            HeaderUtil.createSuccessAlert("taskUpdate"),
            HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return new ResponseEntity<>(
            this.taskService.delete(id),
            HeaderUtil.createSuccessAlert("taskDelete"),
            HttpStatus.CREATED
        );
    }
}
