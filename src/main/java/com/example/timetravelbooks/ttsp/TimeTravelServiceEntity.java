package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.model.AbstractDateRange;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "time_travel_service")
public class TimeTravelServiceEntity {

    @Id
    private String id;

    @DBRef
    @Field(name = "provider")
    private TimeTravelServiceProviderEntity provider;

    @DBRef
    @Field(name = "time_machine")
    private TimeMachineEntity timeMachine;

    private int capacity;

    @Field(name = "date_ranges")
    private List<AbstractDateRange> dateRanges;

    // TODO: It doesn't really make sense to just list a single and a return price, but instead to create algorithms to
    // generate these prices automatically
    @Field(name = "single_price")
    private double singlePrice;

    @Field(name = "return_price")
    private double returnPrice;

    public TimeTravelServiceEntity(
            String id,
            TimeTravelServiceProviderEntity provider,
            TimeMachineEntity timeMachine,
            int capacity,
            List<AbstractDateRange> dateRanges,
            double singlePrice,
            double returnPrice
    ) {
        if (!capacitySupportedByMachine(timeMachine, capacity)) {
            throw new IllegalArgumentException(String.format(
                    "Time Machine %s %s cannot support %d people (Max Capacity: %d)",
                    timeMachine.getMake(),
                    timeMachine.getModel(),
                    capacity,
                    timeMachine.getMaxCapacity()
            ));
        }

        List<AbstractDateRange> rangesNotSupported = dateRangesNotSupportedByMachine(timeMachine, dateRanges);
        if (!rangesNotSupported.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Time Machine %s %s does not support the following date ranges provided:",
                    timeMachine.getMake(),
                    timeMachine.getModel()
            ));
            for (AbstractDateRange dr : rangesNotSupported) {
                sb.append("\n\t").append(dr.toString());
            }

            throw new IllegalArgumentException(sb.toString());
        }

        this.id = id;
        this.provider = provider;
        this.timeMachine = timeMachine;
        this.capacity = capacity;
        this.dateRanges = dateRanges;
        this.singlePrice = singlePrice;
        this.returnPrice = returnPrice;
    }

    public String getId() {
        return id;
    }

    public TimeTravelServiceProviderEntity getProvider() {
        return provider;
    }

    public TimeMachineEntity getTimeMachine() {
        return timeMachine;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<AbstractDateRange> getDateRanges() {
        return dateRanges;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public double getReturnPrice() {
        return returnPrice;
    }

    public boolean hasDateRangeSatisfyingArrivalDate(LocalDate date) {
        for (AbstractDateRange dateRange : dateRanges) {
            if (dateRange.containsDate(date)) return true;
        }
        return false;
    }

    private boolean capacitySupportedByMachine(TimeMachineEntity tm, int capacity) {
        return capacity <= tm.getMaxCapacity();
    }

    private List<AbstractDateRange> dateRangesNotSupportedByMachine(TimeMachineEntity tm, List<AbstractDateRange> dateRanges) {
        List<AbstractDateRange> unsupported = new ArrayList<>();
        for (AbstractDateRange dateRange : dateRanges) {
            if (!tm.getDateRange().fullyContainsDateRange(dateRange)) unsupported.add(dateRange);
        }
        return unsupported;
    }
}
