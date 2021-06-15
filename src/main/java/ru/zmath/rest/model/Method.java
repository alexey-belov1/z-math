package ru.zmath.rest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "method")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String description;
}
