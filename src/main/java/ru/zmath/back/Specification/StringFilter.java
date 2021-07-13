package ru.zmath.back.Specification;

import java.util.Objects;

public class StringFilter extends Filter<String> {

    private String contains;
    private String doesNotContain;

    public StringFilter() {
    }

    public StringFilter(StringFilter filter) {
        super(filter);
        this.contains = filter.contains;
        this.doesNotContain = filter.doesNotContain;
    }

    public StringFilter copy() {
        return new StringFilter(this);
    }

    public String getDoesNotContain() {
        return this.doesNotContain;
    }

    public StringFilter setDoesNotContain(String doesNotContain) {
        this.doesNotContain = doesNotContain;
        return this;
    }

    public String getContains() {
        return this.contains;
    }

    public StringFilter setContains(String contains) {
        this.contains = contains;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                StringFilter that = (StringFilter)o;
                return Objects.equals(this.contains, that.contains) && Objects.equals(this.doesNotContain, that.doesNotContain);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{super.hashCode(), this.contains, this.doesNotContain});
    }

    public String toString() {
        return this.getFilterName() + " [" + (this.getContains() != null ? "contains=" + this.getContains() + ", " : "") + (this.getDoesNotContain() != null ? "doesNotContain=" + this.getDoesNotContain() + ", " : "") + (this.getEquals() != null ? "equals=" + this.getEquals() + ", " : "") + (this.getNotEquals() != null ? "notEquals=" + this.getNotEquals() + ", " : "") + (this.getSpecified() != null ? "specified=" + this.getSpecified() : "") + "]";
    }
}
