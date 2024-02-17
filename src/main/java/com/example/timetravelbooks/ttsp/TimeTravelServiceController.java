package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.request.UserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeTravelServiceController {

    private final TimeTravelServiceRepository repository;

    public TimeTravelServiceController(TimeTravelServiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/services")
    public List<TimeTravelServiceSummary> all() {
        return repository.findAllProjectedBy();
    }

    @GetMapping("/api/services/{id}")
    public TimeTravelServiceEntity one(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/api/services/request")
    public List<TimeTravelServiceSummary> filterServicesByUserRequest(@RequestBody UserRequest userRequest) {
        return repository.findByDateRangeAndCapacity(userRequest.getArrivalDate(), userRequest.getPassengers());
    }

}
