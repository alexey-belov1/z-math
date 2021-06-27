package ru.zmath.rest.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.zmath.rest.model.AttachedFile;
import ru.zmath.rest.model.Status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MethodDTO {

    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String description;
}
