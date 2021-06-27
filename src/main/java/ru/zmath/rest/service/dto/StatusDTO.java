package ru.zmath.rest.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatusDTO {

    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
