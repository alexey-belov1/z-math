package ru.zmath.rest.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.zmath.rest.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskDTO {

    @EqualsAndHashCode.Include
    private int id;

    private int userId;

    private String userLogin;

    private int subjectId;

    private String subjectName;

    private String comment;

    private Calendar deadline;

    private Status status;

    private double cost;

    private double paid;

    private int methodId;

    private String methodName;

    private Calendar created;

    private String contact;

    private String cause;

    private boolean archived;

    private List<AttachedFile> attachedFile = new ArrayList<>();
}
