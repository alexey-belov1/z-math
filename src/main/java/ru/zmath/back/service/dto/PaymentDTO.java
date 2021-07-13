package ru.zmath.back.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentDTO {

    @EqualsAndHashCode.Include
    private Integer id;

    private Calendar created;

    private MethodDTO method;

    private double amount;
}
