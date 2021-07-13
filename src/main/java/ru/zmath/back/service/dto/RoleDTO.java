package ru.zmath.back.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleDTO {

    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
