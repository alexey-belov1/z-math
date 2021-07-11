package ru.zmath.rest.service.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class MethodDTO {

    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String description;
}
