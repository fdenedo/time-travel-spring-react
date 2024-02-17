package com.example.timetravelbooks.ttsp;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "time_travel_service_provider")
public class TimeTravelServiceProviderEntity {

    @Id
    private final String id;

    @NotBlank(message = "Time Travel Service Provider must have a name")
    private String name;

    @NotBlank(message = "Time Travel Service Provider must have a description")
    private String description;

    @DBRef(db = "time_travel_service")
    private List<TimeTravelServiceEntity> services;

    @DBRef(db = "time_machine")
    private List<TimeMachineEntity> machines;

    public TimeTravelServiceProviderEntity(
            String id,
            String name,
            String description,
            List<TimeTravelServiceEntity> services,
            List<TimeMachineEntity> machines
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.services = services;
        this.machines = machines;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<TimeTravelServiceEntity> getServices() {
        return services;
    }

    public List<TimeMachineEntity> getMachines() {
        return machines;
    }

    public void addService(TimeTravelServiceEntity service) {
        this.services.add(service);
    }
}
