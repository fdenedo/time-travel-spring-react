package com.example.timetravelbooks.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a date range between the current date and some date in the future.
 */
public class DateRangeFromPresent extends AbstractDateRange {
    public LocalDate endDate;

    public DateRangeFromPresent(LocalDate endDate) {
        if (endDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException(String.format(
                    "End Date must be before Current Date. Current: %s, End: %s", LocalDate.now(), endDate
            ));

        this.endDate = endDate;
    }

    @Override
    public LocalDate getStartDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "startDate=" + LocalDate.now() + " (current)" +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRangeFromPresent that = (DateRangeFromPresent) o;
        return Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate);
    }
}
