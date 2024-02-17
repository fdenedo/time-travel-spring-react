package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.model.DateRange;
import com.example.timetravelbooks.model.DateRangeFromPresent;
import com.example.timetravelbooks.model.DateRangeToPresent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeMachineInitializer {

    private final TimeMachineRepository timeMachineRepository;

    @Autowired
    public TimeMachineInitializer(TimeMachineRepository timeMachineRepository) {
        this.timeMachineRepository = timeMachineRepository;
    }

    @PostConstruct
    public void init() {
        List<TimeMachineEntity> timeMachines = createTimeMachines();
        timeMachineRepository.saveAll(timeMachines);
    }

    private List<TimeMachineEntity> createTimeMachines() {
        List<TimeMachineEntity> timeMachines = new ArrayList<>();

        TimeMachineEntity tm1 = new TimeMachineEntity(
                "1",
                "Timeaudi",
                "TTX1",
                """
                        Compact and reliable, the TTX1 from Timeaudi is perfect for short hops into the past, allowing \
                        travelers to explore events up to 1 million years ago.
                        """,
                1,
                new DateRange(
                        LocalDate.of(-1_000_000, 10, 15),
                        LocalDate.of(20_000, 2, 15)
                )
        );
        timeMachines.add(tm1);

        TimeMachineEntity tm2 = new TimeMachineEntity(
                "2",
                "Timeaudi",
                "TTX5",
                """
                        For adventurers seeking a bit more room and versatility, the TTX5 offers ample capacity and a \
                        range spanning from 240,000 BC to 6,000 AD.
                        """,
                5,
                new DateRange(
                        LocalDate.of(-240_000, 12, 1),
                        LocalDate.of(6_000, 5, 25)
                )
        );
        timeMachines.add(tm2);

        TimeMachineEntity tm3 = new TimeMachineEntity(
                "3",
                "Timeaudi",
                "TM-570",
                """
                        With its robust design and extended range, the TM-570 is ideal for exploring the depths of \
                        history, reaching back to 150,000 BC while venturing into the future until 5,000 AD.
                        """,
                5,
                new DateRange(
                        LocalDate.of(-150_000, 12, 1),
                        LocalDate.of(5_000, 5, 25)
                )
        );
        timeMachines.add(tm3);

        TimeMachineEntity tm4 = new TimeMachineEntity(
                "4",
                "Timeaudi",
                "TM-870",
                """
                        Embark on epic temporal journeys with the TM-870, boasting an impressive capacity and the \
                        ability to traverse time from 140,000 BC to 4,800 AD.
                        """,
                8,
                new DateRange(
                        LocalDate.of(-140_000, 1, 1),
                        LocalDate.of(4_800, 8, 30)
                )
        );
        timeMachines.add(tm4);

        TimeMachineEntity tm5 = new TimeMachineEntity(
                "5",
                "Fold Ventura",
                "Galaxy",
                """
                        Discover the wonders of the cosmos with Fold Ventura's Galaxy model, offering comfortable \
                        seating for two and journeys extending up to 35,000 AD.
                        """,
                2,
                new DateRangeFromPresent(LocalDate.of(35_000, 1, 17))
        );
        timeMachines.add(tm5);

        TimeMachineEntity tm6 = new TimeMachineEntity(
                "6",
                "Fold Ventura",
                "Astronaut",
                """
                        Designed for the intrepid explorers, the Astronaut model from Fold Ventura provides ample \
                        space for three travelers, guiding them through time until 25,000 AD.
                        """,
                3,
                new DateRangeFromPresent(LocalDate.of(25_000, 5, 6))
        );
        timeMachines.add(tm6);

        TimeMachineEntity tm7 = new TimeMachineEntity(
                "7",
                "Horizon",
                "R3BYA",
                """
                        Unlock the mysteries of ancient civilizations with Horizon's R3BYA model, capable of delving \
                        into the depths of history up to 3,000 million years ago.
                        """,
                2,
                new DateRangeToPresent(LocalDate.of(-999_999_999, 1, 1))
        );
        timeMachines.add(tm7);

        TimeMachineEntity tm8 = new TimeMachineEntity(
                "8",
                "QuantumCorp",
                "QM-1000",
                """
                        Experience the simplicity and elegance of time travel with QuantumCorp's QM-1000, offering solitary \
                        journeys from 10,000 BC to 3,000 AD.
                        """,
                1,
                new DateRange(
                        LocalDate.of(-10_000, 1, 1),
                        LocalDate.of(3_000, 12, 31)
                )
        );
        timeMachines.add(tm8);

        TimeMachineEntity tm9 = new TimeMachineEntity(
                "9",
                "QuantumCorp",
                "QM-2000",
                """
                        Venture into the past and future with QuantumCorp's QM-2000, accommodating two travelers on journeys \
                        spanning from 10,000 BC to the present day.
                        """,
                2,
                new DateRangeToPresent(
                        LocalDate.of(-10_000, 1, 1)
                )
        );
        timeMachines.add(tm9);

        TimeMachineEntity tm10 = new TimeMachineEntity(
                "10",
                "QuantumCorp",
                "QM-3000",
                """
                        For groups of three, the QM-3000 from QuantumCorp provides the perfect balance of capacity and \
                        flexibility, exploring time from 4,000 BC onward.
                        """,
                3,
                new DateRangeToPresent(LocalDate.of(-4_000, 1, 1))
        );
        timeMachines.add(tm10);

        TimeMachineEntity tm11 = new TimeMachineEntity(
                "11",
                "QuantumCorp",
                "QM-4000",
                """
                        Embrace the power of time slicing with QuantumCorp's QM-4000, accommodating four travelers on journeys \
                        extending from 4,000 BC into the future.
                        """,
                4,
                new DateRangeToPresent(LocalDate.of(-4_000, 1, 1))
        );
        timeMachines.add(tm11);

        TimeMachineEntity tm12 = new TimeMachineEntity(
                "12",
                "Temporal Dynamics",
                "TD-5000",
                """
                        Journey to the distant past or far future with Temporal Dynamics' TD-5000, offering solitary \
                        adventures from 1,000 million years ago to 100,000 AD.
                        """,
                1,
                new DateRange(
                        LocalDate.of(-999_999_999, 1, 1),
                        LocalDate.of(100_000, 12, 31)
                )
        );
        timeMachines.add(tm12);

        TimeMachineEntity tm13 = new TimeMachineEntity(
                "13",
                "NexGen",
                "Jabba",
                """
                        Discover new realms of temporal exploration with NexGen's Jabba model, accommodating groups of three \
                        on journeys extending until 3,000 AD.
                        """,
                3,
                new DateRangeFromPresent(LocalDate.of(3_000, 12, 31))
        );
        timeMachines.add(tm13);

        TimeMachineEntity tm14 = new TimeMachineEntity(
                "14",
                "NexGen",
                "Hutt",
                """
                        For the ultimate in versatility, NexGen's Hutt model provides ample space for three travelers, guiding \
                        them through time until 5,000 AD.
                        """,
                3,
                new DateRangeFromPresent(LocalDate.of(5_000, 12, 31))
        );
        timeMachines.add(tm14);

        TimeMachineEntity tm15 = new TimeMachineEntity(
                "15",
                "Infinity Technologies",
                "Continuum",
                """
                        Embark on infinite temporal voyages with Infinity Technologies' Continuum model, accommodating two \
                        travelers on journeys spanning from 1,000 million years ago to 1,000,000 AD.
                        """,
                2,
                new DateRange(
                        LocalDate.of(-999_999_999, 1, 1),
                        LocalDate.of(1_000_000, 12, 31)
                )
        );
        timeMachines.add(tm15);

        TimeMachineEntity tm16 = new TimeMachineEntity(
                "16",
                "Infinity Technologies",
                "TimeRipper",
                """
                        Unravel the mysteries of the universe with Infinity Technologies' TimeRipper model, offering solitary \
                        travelers the opportunity to explore time from 13.5 billion years ago to 1 billion AD.
                        """,
                1,
                new DateRange(
                        LocalDate.of(-999_999_999, 1, 1),
                        LocalDate.of(999_999_999, 12, 31)
                )
        );
        timeMachines.add(tm16);

        return timeMachines;
    }
}