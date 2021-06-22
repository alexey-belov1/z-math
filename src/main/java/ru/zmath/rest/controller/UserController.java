package ru.zmath.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.zmath.rest.controller.util.HeaderUtil;
import ru.zmath.rest.errors.EmailAlreadyUsedException;
import ru.zmath.rest.errors.LoginAlreadyUsedException;
import ru.zmath.rest.model.Role;
import ru.zmath.rest.model.User;
import ru.zmath.rest.service.UserService;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
/*@PreAuthorize("hasRole('ADMIN')")*/
public class UserController {

    private final String entity = "user";
    private final UserService userService;
    private BCryptPasswordEncoder encoder;

    public UserController(final UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        Optional<User> res = this.userService.findById(id);
        return new ResponseEntity<>(
                res.orElseGet(User::new),
                res.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> create(@RequestBody User user) {

        if (userService.findByName(user.getLogin()).isPresent()) {
            throw new LoginAlreadyUsedException();
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        Role role = new Role();
        role.setId(1);
        user.setRole(role);
        user.setCreated(GregorianCalendar.getInstance());
        user.setPassword(encoder.encode(user.getPassword()));
        User result = userService.save(user);

        return new ResponseEntity<>(
                result,
                HeaderUtil.createSuccessAlert(entity),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody User user) {
        return this.userService.update(user)
                ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        User user = new User();
        user.setId(id);
        return this.userService.delete(user)
                ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
