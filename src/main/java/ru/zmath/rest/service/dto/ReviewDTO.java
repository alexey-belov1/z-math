package ru.zmath.rest.service.dto;

import lombok.*;

import java.util.Calendar;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    @EqualsAndHashCode.Include
    private int id;

    private int userId;

    private String userLogin;

    private Calendar created;

    private String text;
}
