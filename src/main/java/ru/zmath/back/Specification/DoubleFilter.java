package ru.zmath.back.Specification;

public class DoubleFilter extends RangeFilter<Double> {

    public DoubleFilter() {
    }

    public DoubleFilter(DoubleFilter filter) {
        super(filter);
    }

    public DoubleFilter copy() {
        return new DoubleFilter(this);
    }
}
