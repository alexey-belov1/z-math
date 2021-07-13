package ru.zmath.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.zmath.rest.model.Subject;
import ru.zmath.rest.service.*;
import ru.zmath.rest.service.dto.UserDTO;

import java.util.Calendar;

@Component
public class InitDataDB implements ApplicationRunner {

    @Autowired
    private AttachedFileService attachedFileService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    public void run(ApplicationArguments args) {
/*        UserDTO userDTO = new UserDTO();

        userDTO.setPassword("123");
        userDTO.setLogin("123");
        userDTO.setCreated(Calendar.getInstance());
        this.userService.save(userDTO);
        this.userService.save(userDTO);
        this.userService.save(userDTO);*/
    }
}
