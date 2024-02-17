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
        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 60);

        return LocalTime.of(hour, minute);
    }

    public JourneyResponse createJourneyFromService(
            TimeTravelServiceSummary service,
            LocalDate departureDate,
            LocalDate arrivalDate,
            int passengers,
            LocalDate returnDate,
            Integer journeyLengthInDays
    ) {
        boolean isReturnJourney = returnDate != null;

        double totalPrice;
        Period journeyLength = null;

        if (isReturnJourney) {
            totalPrice = passengers * service.getReturnPrice();
            journeyLength = Period.ofDays(journeyLengthInDays);
        } else {
            totalPrice = passengers * service.getSinglePrice();
        }

        return new JourneyResponse(
                service.getProviderName(),
                service.getTimeMachineMakeAndModel(),
                departureDate,
                generateRandomTime(),
                arrivalDate,
                journeyLength,
                returnDate,
                passengers,
                totalPrice
        );
    }

}
