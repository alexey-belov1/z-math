package ru.zmath.rest.service.dto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AttachedFileDTO implements Serializable {

    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String size;

    private String extension;

    private String path;

    private String type;

    private int taskId;
}
