package com.example.timetravelbooks.ttsp;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        List<TimeTravelServiceProviderEntity> ttsps = new ArrayList<>();

        TimeTravelServiceProviderEntity ttsp1 = new TimeTravelServiceProviderEntity(
                "1",
                "Rewind",
                """
                Whether you are looking to remember events in your childhood, relive past events, or \
                correct past wrongs, Rewind is the provider for you!
                """,
                new ArrayList<>(),
                new ArrayList<>()
        );
        ttsps.add(ttsp1);

        TimeTravelServiceProviderEntity ttsp2 = new TimeTravelServiceProviderEntity(
                "2",
                "FirstTravel",
                """
                Technically founded in 8000 BC, FirstTravel specialises in luxury jaunts for people who wish to have an \
                incredible one-of-a-kind experience. Go back in time and see how your ancestors used to dance, or see \
                how the future will unfold.
                """,
                new ArrayList<>(),
                new ArrayList<>()
        );
        ttsps.add(ttsp2);

        return ttsps;
    }
}