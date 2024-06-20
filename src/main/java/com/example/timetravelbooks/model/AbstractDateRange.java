package com.example.timetravelbooks.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a date range from some start date to some end date. This interface provides methods for checking if a
 * certain date or date range lies between these two dates. Implementations of these methods should evaluate bounds
 * inclusively.
 */
public abstract class AbstractDateRange {

    /**
     * Gets the start date
     *
     * @return  the startDate
     */
    public abstract LocalDate getStartDate();

    /**
     * Gets the end date
     *
     * @return  the endDate
     */
    public abstract LocalDate getEndDate();

    /**
     * Returns true if the provided date is between the start date and the end date of this DateRange (inclusive).
     *
     * @param date  the date to be checked
     * @return      true if this.startDate <= date <= this.endDate
     */
    public boolean containsDate(LocalDate date) {
        return !(date.isBefore(getStartDate()) || date.isAfter(getEndDate()));
    }

    /**
     * Returns true if the provided dateRange is fully contained within this date range (inclusive). If any date within
     * the date range provided as argument lies outside this date range, this returns false.
     *
     * @param dateRange the DateRange to be checked
     * @return          true if this.startDate <= dateRange.startDate && this.endDate >= dateRange.endDate
     */
    public boolean fullyContainsDateRange(AbstractDateRange dateRange) {
        return !(this.equals(dateRange)
                || dateRange.getStartDate().isBefore(this.getStartDate())
                || dateRange.getEndDate().isAfter(this.getEndDate())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDateRange dateRange = (AbstractDateRange) o;
        return Objects.equals(this.getStartDate(), dateRange.getStartDate())
                && Objects.equals(this.getEndDate(), dateRange.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getStartDate(), this.getEndDate());
    }

}
