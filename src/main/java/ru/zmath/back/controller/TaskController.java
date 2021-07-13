package ru.zmath.back.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zmath.back.controller.util.HeaderUtil;
import ru.zmath.back.controller.util.PaginationUtil;
import ru.zmath.back.service.TaskService;
import ru.zmath.back.service.criteria.TaskCriteria;
import ru.zmath.back.service.query.TaskQueryService;
import ru.zmath.back.service.dto.TaskDTO;

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
        return new ResponseEntity<>(
            page.getContent(),
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page),
            HttpStatus.OK
        );
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
        TaskDTO result = this.taskService.save(taskDTO, files);
        return new ResponseEntity<>(
            result,
            HeaderUtil.createSuccessAlert("taskCreate", String.valueOf(result.getId())),
            HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/task", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> update(@RequestPart("taskDTO") TaskDTO taskDTO,
                                          @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        return this.taskService.update(taskDTO, files)
            ? ResponseEntity.ok()
                .headers(HeaderUtil.createSuccessAlert("taskUpdate", String.valueOf(taskDTO.getId()))).build()
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return this.taskService.delete(id)
            ? ResponseEntity.ok().headers(HeaderUtil.createSuccessAlert("taskDelete", String.valueOf(id))).build()
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping("/task/payment")
    public ResponseEntity<Void> setPayment(@RequestBody TaskDTO taskDTO) {
        return this.taskService.setPayment(taskDTO)
            ? ResponseEntity.ok()
                .headers(HeaderUtil.createSuccessAlert("taskPayment", String.valueOf(taskDTO.getId()))).build()
            : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
