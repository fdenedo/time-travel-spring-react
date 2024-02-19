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
public class TTSPInitializer {

    private final TTSPRepository ttspRepository;

    @Autowired
    public TTSPInitializer(TTSPRepository ttspRepository) {
        this.ttspRepository = ttspRepository;
    }

    @PostConstruct
    public void init() {
        List<TimeTravelServiceProviderEntity> ttsps = createTTSPs();
        ttspRepository.saveAll(ttsps);
    }

    private List<TimeTravelServiceProviderEntity> createTTSPs() {
        try (InputStream serviceprovidersFileAsStream = getClass().getResourceAsStream("/static/ttsprovider.csv")) {
            if (serviceprovidersFileAsStream == null) throw new NullPointerException("Could not find CSV file for service providers");
            Reader reader = new InputStreamReader(serviceprovidersFileAsStream);
            return new CsvToBeanBuilder<TTSPCsvRepr>(reader)
                    .withType(TTSPCsvRepr.class)
                    .build()
                    .parse()
                    .stream().map(TTSPCsvRepr::toEntity)
                    .toList();
        } catch (Exception e) {
            System.out.println("Error reading CSV file for time travel services:");
            // TODO: Look into adding more robust logging
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}