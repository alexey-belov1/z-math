package ru.zmath.rest.service.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class StatusDTO implements Serializable {

    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
