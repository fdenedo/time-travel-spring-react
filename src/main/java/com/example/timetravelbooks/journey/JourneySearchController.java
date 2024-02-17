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

    private final JourneyManager manager;

    public JourneySearchController(TimeTravelServiceRepository repository, JourneyManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    @GetMapping("/api/journeys")
    public List<JourneyResponse> journeysSatisfyingUserRequest(
            @RequestParam                   LocalDate departureDate,
            @RequestParam                   LocalDate arrivalDate,
            @RequestParam                   int passengers,
            @RequestParam(required = false) LocalDate returnDate,
            @RequestParam(required = false) Integer journeyLengthInDays
    ) {
        List<TimeTravelServiceSummary> services = repository.findByDateRangeAndCapacity(
                arrivalDate,
                passengers
        );

        return services.stream()
                .map(service -> manager.createJourneyFromService(
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
