package com.example.timetravelbooks.journey;

import com.example.timetravelbooks.ttsp.TimeTravelServiceRepository;
import com.example.timetravelbooks.ttsp.TimeTravelServiceSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class JourneySearchController {

    private final TimeTravelServiceRepository repository;
    private final JourneyManager journeyManager;

    public JourneySearchController(
            TimeTravelServiceRepository repository,
            JourneyManager journeyManager
    ) {
        this.repository = repository;
        this.journeyManager = journeyManager;
    }

    @GetMapping("/api/journeys")
    public List<JourneyResponse> journeysSatisfyingUserRequest(
            @RequestParam                   LocalDate departureDate,
            @RequestParam                   LocalDate arrivalDate,
            @RequestParam                   int passengers,
            @RequestParam(required = false) LocalDate returnDate,
            @RequestParam(required = false) Integer journeyLengthInDays
    ) {
        LocalDate currentDate = LocalDate.now();
        List<TimeTravelServiceSummary> services;

        if (arrivalDate.isBefore(currentDate)) {
            services = repository.findByPastDateRequest(arrivalDate, passengers);
        } else if (arrivalDate.isAfter(currentDate)) {
            services = repository.findByFutureDateRequest(arrivalDate, passengers);
        } else {
            services = repository.findByPresentDateRequest(arrivalDate, passengers);
        }
        return services.stream()
                .map(service -> journeyManager.createJourneyFromService(
                        service,
                        departureDate,
                        arrivalDate,
                        passengers,
                        returnDate,
                        journeyLengthInDays
                ))
                .toList();
    }

}
