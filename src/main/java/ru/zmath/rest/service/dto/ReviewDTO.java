package ru.zmath.rest.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReviewDTO {

    @EqualsAndHashCode.Include
    private int id;

    private int userId;

    private String userLogin;

    private Calendar created;

    private String text;
}
