package ru.zmath.back.service.criteria;

import lombok.Getter;
import lombok.Setter;
import ru.zmath.back.Specification.BooleanFilter;
import ru.zmath.back.Specification.IntegerFilter;

@Getter
@Setter
public class TaskCriteria {

    private IntegerFilter subjectId;

    private IntegerFilter statusId;

    private BooleanFilter archived;
}
