package ru.zmath.rest.Specification;

public class LongFilter extends RangeFilter<Long> {

    public LongFilter() {
    }

    public LongFilter(LongFilter filter) {
        super(filter);
    }

    public LongFilter copy() {
        return new LongFilter(this);
    }
}
