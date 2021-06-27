package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.controller.util.HeaderUtil;
import ru.zmath.rest.model.User;
import ru.zmath.rest.service.UserService;
import ru.zmath.rest.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
/*@PreAuthorize("hasRole('ADMIN')")*/
public class UserController {

    private final String entity = "user";
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable int id) {
        Optional<UserDTO> res = this.userService.findById(id);
        return new ResponseEntity<>(
                res.orElseGet(UserDTO::new),
                res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                userService.save(userDTO),
                HeaderUtil.createSuccessAlert(entity),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO) {
        return this.userService.update(userDTO)
                ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        return this.userService.delete(userDTO)
                ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
