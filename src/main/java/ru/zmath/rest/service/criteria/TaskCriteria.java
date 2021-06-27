package ru.zmath.rest.service.criteria;

import lombok.Getter;
import lombok.Setter;
import ru.zmath.rest.Specification.*;

@Getter
@Setter
public class TaskCriteria {

    private IntegerFilter subjectId;

    private IntegerFilter statusId;

    private BooleanFilter archived;
}
