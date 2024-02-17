package com.example.timetravelbooks.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UserRequest {

    @NotNull
    LocalDate departureDate;

    @NotNull
    LocalDate arrivalDate;

    @NotNull
    int passengers;

    LocalDate returnDate;

    int journeyLengthInDays;

    public UserRequest(
            LocalDate departureDate,
            LocalDate arrivalDate,
            int passengers,
            LocalDate returnDate,
            int journeyLengthInDays
    ) {
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passengers = passengers;
        this.returnDate = returnDate;
        this.journeyLengthInDays = journeyLengthInDays;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public int getPassengers() {
        return passengers;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getJourneyLengthInDays() {
        return journeyLengthInDays;
    }
}
