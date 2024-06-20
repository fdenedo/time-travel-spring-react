package com.example.timetravelbooks.journey;

import com.example.timetravelbooks.ttsp.TimeTravelServiceSummary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JourneyManager {

    public LocalTime generateRandomTime() {
        // Time can be generated from date, as well as some kind of user token, the service ID and something else
        // use the hex value of the code (somehow)
        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 60);

        return LocalTime.of(hour, minute);
    }

    private LocalDate calculateReturnDepartureDate(LocalDate targetArrivalDate, Period journeyLength) {
        return targetArrivalDate.plus(journeyLength);
    }

    public JourneyResponse createJourneyFromService(
            TimeTravelServiceSummary serviceSummary,
            LocalDate departureDate,
            LocalDate arrivalDate,
            int passengers,
            LocalDate returnArrivalDate,
            Integer journeyLengthInDays
    ) {
        boolean isReturnJourney = returnArrivalDate != null;

        double totalPrice;
        Period journeyLength = null;
        LocalDate returnDepartureDate = null;

        if (isReturnJourney) {
            totalPrice = passengers * serviceSummary.getReturnPrice();
            journeyLength = Period.ofDays(journeyLengthInDays);
            returnDepartureDate = calculateReturnDepartureDate(arrivalDate, journeyLength);
        } else {
            totalPrice = passengers * serviceSummary.getSinglePrice();
        }

        return new JourneyResponse(
                serviceSummary.getId(),
                serviceSummary.getProviderName(),
                serviceSummary.getTimeMachineMakeAndModel(),
                departureDate,
                generateRandomTime(),
                arrivalDate,
                journeyLength,
                returnDepartureDate,
                returnArrivalDate,
                passengers,
                totalPrice
        );
    }

}
