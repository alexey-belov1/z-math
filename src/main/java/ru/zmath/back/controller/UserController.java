package ru.zmath.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.zmath.back.controller.util.HeaderUtil;
import ru.zmath.back.service.UserService;
import ru.zmath.back.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(
            this.userService.findAll(),
            HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable int id) {
        Optional<UserDTO> res = this.userService.findById(id);
        return new ResponseEntity<>(
            res.orElseGet(UserDTO::new),
            res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/user/sign-up")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                userService.save(userDTO),
                HeaderUtil.createSuccessAlert("userCreate", userDTO.getLogin()),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/user")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO) {
        return this.userService.update(userDTO)
            ? ResponseEntity.ok().build()
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return this.userService.delete(id)
            ? ResponseEntity.ok().build()
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
