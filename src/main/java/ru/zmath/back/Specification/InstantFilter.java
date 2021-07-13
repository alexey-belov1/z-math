package ru.zmath.back.Specification;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public class InstantFilter extends RangeFilter<Instant> {
    private static final long serialVersionUID = 1L;

    public InstantFilter() {
    }

    public InstantFilter(InstantFilter filter) {
        super(filter);
    }

    public InstantFilter copy() {
        return new InstantFilter(this);
    }

    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    public InstantFilter setEquals(Instant equals) {
        super.setEquals(equals);
        return this;
    }

    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    public InstantFilter setNotEquals(Instant equals) {
        super.setNotEquals(equals);
        return this;
    }

    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    public InstantFilter setGreaterThan(Instant equals) {
        super.setGreaterThan(equals);
        return this;
    }

    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    public InstantFilter setGreaterThanOrEqual(Instant equals) {
        super.setGreaterThanOrEqual(equals);
        return this;
    }

    /** @deprecated */
    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    @Deprecated
    public InstantFilter setGreaterOrEqualThan(Instant equals) {
        super.setGreaterOrEqualThan(equals);
        return this;
    }

    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    public InstantFilter setLessThan(Instant equals) {
        super.setLessThan(equals);
        return this;
    }

    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    public InstantFilter setLessThanOrEqual(Instant equals) {
        super.setLessThanOrEqual(equals);
        return this;
    }

    /** @deprecated */
    @DateTimeFormat(
        iso = DateTimeFormat.ISO.DATE_TIME
    )
    @Deprecated
    public InstantFilter setLessOrEqualThan(Instant equals) {
        super.setLessOrEqualThan(equals);
        return this;
    }
}
