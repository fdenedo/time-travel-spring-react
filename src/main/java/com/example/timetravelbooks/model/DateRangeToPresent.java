package com.example.timetravelbooks.model;

import java.time.LocalDate;

/**
 * Represents a date range between some date in the past and the current date.
 */
public class DateRangeToPresent extends AbstractDateRange {
    public LocalDate startDate;

    public DateRangeToPresent(LocalDate startDate) {
        if (startDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException(String.format(
                    "Start Date must be before Current Date. Start: %s, Current: %s", startDate, LocalDate.now()
            ));

        this.startDate = startDate;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "startDate=" + this.startDate +
                ", endDate=" + LocalDate.now() + " (current)" +
                '}';
    }
}
