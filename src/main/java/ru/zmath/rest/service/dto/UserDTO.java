package ru.zmath.rest.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.zmath.rest.model.Role;

import java.util.Calendar;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {

    @EqualsAndHashCode.Include
    private int id;

    private String login;

    private String email;

    private String password;

    private Role role;

    private Calendar created;

    private double balance;
}
