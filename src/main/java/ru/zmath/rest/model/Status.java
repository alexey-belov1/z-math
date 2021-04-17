package ru.zmath.rest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "status")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
}
