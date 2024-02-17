package com.example.timetravelbooks.model;

import java.time.LocalDate;
import java.util.Objects;

public class DateRange extends AbstractDateRange {
    public LocalDate startDate;
    public LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate))
            throw new IllegalArgumentException(String.format(
                    "Start Date must be before End Date. Start: %s, End: %s", startDate, endDate
            ));

        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(startDate, dateRange.startDate) && Objects.equals(endDate, dateRange.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
