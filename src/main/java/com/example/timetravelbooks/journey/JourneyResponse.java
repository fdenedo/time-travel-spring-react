package com.example.timetravelbooks.journey;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

public class JourneyResponse {

    @NotNull
    String providerName;

    @NotNull
    String timeMachineMakeAndModel;

    @NotNull
    LocalDate departureDate;

    @NotNull
    LocalTime departureTime;

    @NotNull
    LocalDate targetArrivalDate;

    TemporalAmount journeyLength;

    LocalDate targetReturnDate;

    @NotNull
    int passengers;

    @NotNull
    double totalPrice;

    public JourneyResponse(
            String providerName,
            String timeMachineMakeAndModel,
            LocalDate departureDate,
            LocalTime departureTime,
            LocalDate targetArrivalDate,
            TemporalAmount journeyLength,
            LocalDate targetReturnDate,
            int passengers,
            double totalPrice
    ) {
        this.providerName = providerName;
        this.timeMachineMakeAndModel = timeMachineMakeAndModel;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.targetArrivalDate = targetArrivalDate;
        this.journeyLength = journeyLength;
        this.targetReturnDate = targetReturnDate;
        this.passengers = passengers;
        this.totalPrice = totalPrice;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getTimeMachineMakeAndModel() {
        return timeMachineMakeAndModel;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalDate getTargetArrivalDate() {
        return targetArrivalDate;
    }

    public TemporalAmount getJourneyLength() {
        return journeyLength;
    }

    public LocalDate getTargetReturnDate() {
        return targetReturnDate;
    }

    public int getPassengers() {
        return passengers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
