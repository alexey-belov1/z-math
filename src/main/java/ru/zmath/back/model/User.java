package ru.zmath.back.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String login;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Calendar created;
}
