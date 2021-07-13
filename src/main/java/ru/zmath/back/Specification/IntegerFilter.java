package ru.zmath.back.Specification;

public class IntegerFilter extends RangeFilter<Integer> {

    public IntegerFilter() {
    }

    public IntegerFilter(IntegerFilter filter) {
        super(filter);
    }

    public IntegerFilter copy() {
        return new IntegerFilter(this);
    }
}
