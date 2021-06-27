package ru.zmath.rest.Specification;

import java.util.Calendar;

public class CalendarFilter extends RangeFilter<Calendar> {

    public CalendarFilter() {
    }

    public CalendarFilter(CalendarFilter filter) {
        super(filter);
    }

    public CalendarFilter copy() {
        return new CalendarFilter(this);
    }
}
