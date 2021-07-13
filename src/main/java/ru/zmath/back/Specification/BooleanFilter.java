package ru.zmath.back.Specification;

public class BooleanFilter extends Filter<Boolean> {

    public BooleanFilter() {
    }

    public BooleanFilter(BooleanFilter filter) {
        super(filter);
    }

    public BooleanFilter copy() {
        return new BooleanFilter(this);
    }
}
