package ru.zmath.rest.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    private SubjectDTO subject;

    private String comment;

    private Calendar deadline;

    private StatusDTO status;

    private double cost;

    private double paid;

    private PaymentDTO payment;

    private Calendar created;

    private String contact;

    private String cause;

    private boolean archived;

    private List<AttachedFileDTO> attachedFiles = new ArrayList<>();
}
