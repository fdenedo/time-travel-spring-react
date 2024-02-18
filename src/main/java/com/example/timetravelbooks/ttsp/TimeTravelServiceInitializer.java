package com.example.timetravelbooks.ttsp;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
        try (InputStream servicesFileAsStream = getClass().getResourceAsStream("/static/time-travel-services.csv")) {
            if (servicesFileAsStream == null) throw new NullPointerException("Could not find CSV file for services");
            Reader reader = new InputStreamReader(servicesFileAsStream);
            return new CsvToBeanBuilder<TimeTravelServiceCsvRepr>(reader)
                    .withType(TimeTravelServiceCsvRepr.class)
                    .build()
                    .parse()
                    .stream().map(service -> service.toEntity(this.timeMachineRepository, this.ttspRepository))
                    .toList();
        } catch (Exception e) {
            System.out.println("Error reading CSV file for time travel services:");
            // TODO: Look into adding more robust logging
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
