package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.model.AbstractDateRange;
import com.example.timetravelbooks.model.DateRangeFromPresent;
import com.example.timetravelbooks.model.DateRangeToPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a time machine make and model. The only fields that are used to determine the capabilities of the Time
 * Machine are maxCapacity and dateRange.
 * If the time machine can only support time travel into the past, define the dateRange with a {@link DateRangeToPresent}.
 * If it can only support time travel into the future, define a {@link DateRangeFromPresent}.
 */
@Document(collection = "time_machine")
public class TimeMachineEntity {

    @Id
    private final String id;

    @NotBlank(message = "Time Machine must have a Make")
    private final String make;

    @NotBlank(message = "Time Machine must have a Model")
    private final String model;

    private final String description;

    @NotNull(message = "Time Machine must have a max capacity")
    @Field(name = "max_capacity")
    private final int maxCapacity;

    @NotNull(message = "Time Travel must have a defined date range")
    @Field(name = "date_range")
    private final AbstractDateRange dateRange;

    public TimeMachineEntity(
            String id,
            String make,
            String model,
            String description,
            int maxCapacity,
            AbstractDateRange dateRange) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.dateRange = dateRange;
    }

    public String getId() {
        return id;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getDescription() {
        return this.description;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public AbstractDateRange getDateRange() {
        return dateRange;
    }
}
