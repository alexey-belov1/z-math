package ru.zmath.back.service.dto;

import lombok.*;

import java.util.Calendar;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @EqualsAndHashCode.Include
    private int id;

    private String login;

    private String email;

    private String password;

    private RoleDTO role;

    private Calendar created;
}
