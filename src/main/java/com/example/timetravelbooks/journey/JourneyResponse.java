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

    LocalDate targetReturnDepartureDate;

    LocalDate targetReturnArrivalDate;

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
            LocalDate targetReturnDepartureDate,
            LocalDate targetReturnArrivalDate,
            int passengers,
            double totalPrice
    ) {
        this.providerName = providerName;
        this.timeMachineMakeAndModel = timeMachineMakeAndModel;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.targetArrivalDate = targetArrivalDate;
        this.journeyLength = journeyLength;
        this.targetReturnDepartureDate = targetReturnDepartureDate;
        this.targetReturnArrivalDate = targetReturnArrivalDate;
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

    public LocalDate getTargetReturnDepartureDate() {
        return targetReturnDepartureDate;
    }

    public LocalDate getTargetReturnArrivalDate() {
        return targetReturnArrivalDate;
    }

    public int getPassengers() {
        return passengers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
