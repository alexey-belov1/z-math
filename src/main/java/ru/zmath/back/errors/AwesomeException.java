package ru.zmath.back.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AwesomeException {
    private String message;
}
