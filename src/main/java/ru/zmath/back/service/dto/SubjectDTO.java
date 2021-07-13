package ru.zmath.back.service.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO implements Serializable {

    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
