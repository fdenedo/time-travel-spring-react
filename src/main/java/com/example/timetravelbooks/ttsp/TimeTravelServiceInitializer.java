package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.model.DateRange;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeTravelServiceInitializer {

    private final TimeTravelServiceRepository timeTravelServiceRepository;

    private final TTSPRepository ttspRepository;

    private final TimeMachineRepository timeMachineRepository;

    @Autowired
    public TimeTravelServiceInitializer(
            TimeTravelServiceRepository timeTravelServiceRepository,
            TTSPRepository ttspRepository,
            TimeMachineRepository timeMachineRepository
    ) {
        this.timeTravelServiceRepository = timeTravelServiceRepository;
        this.ttspRepository = ttspRepository;
        this.timeMachineRepository = timeMachineRepository;
    }

    @PostConstruct
    public void init() {
        List<TimeTravelServiceEntity> services = createTimeTravelServices();
        timeTravelServiceRepository.saveAll(services);
    }

    private List<TimeTravelServiceEntity> createTimeTravelServices() {
        List<TimeTravelServiceEntity> services = new ArrayList<>();

        // SERVICE 1
        List<DateRange> s1ranges = new ArrayList<>();
        s1ranges.add(new DateRange(
                LocalDate.of(-10000, 1, 1),
                LocalDate.of(1850, 1, 1)
        ));

        TimeTravelServiceEntity service1 = new TimeTravelServiceEntity(
                "1",
                ttspRepository.findByName("FirstTravel"),
                timeMachineRepository.findByMakeAndModel("QuantumCorp", "QM-2000").get(0),
                2,
                s1ranges,
                1000000.0,
                3000000.0
        );
        services.add(service1);

        // SERVICE 2
        List<DateRange> s2ranges = new ArrayList<>();
        s2ranges.add(new DateRange(
                LocalDate.of(-3000, 1, 1),
                LocalDate.of(100, 1, 1)
        ));

        TimeTravelServiceEntity service2 = new TimeTravelServiceEntity(
                "2",
                ttspRepository.findByName("FirstTravel"),
                timeMachineRepository.findByMakeAndModel("QuantumCorp", "QM-4000").get(0),
                4,
                s2ranges,
                1000000.0,
                3000000.0
        );
        services.add(service2);

        // SERVICE 3
        List<DateRange> s3ranges = new ArrayList<>();
        s3ranges.add(new DateRange(
                LocalDate.of(0, 1, 1),
                LocalDate.of(2024, 2, 16)
        ));

        TimeTravelServiceEntity service3 = new TimeTravelServiceEntity(
                "3",
                ttspRepository.findByName("Rewind"),
                timeMachineRepository.findByMakeAndModel("QuantumCorp", "QM-3000").get(0),
                2,
                s3ranges,
                800000.0,
                3200000.0
        );
        services.add(service3);

        return services;
    }
}
