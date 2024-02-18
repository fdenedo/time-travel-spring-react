package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.model.AbstractDateRange;
import com.example.timetravelbooks.model.DateRange;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import java.util.List;

public class TimeTravelServiceCsvRepr {

    @CsvBindByName(required = true)
    private String id;

    @CsvBindByName(column = "provider", required = true)
    private String providerName;

    @CsvBindByName(column = "time_machine", required = true)
    private String timeMachine;

    @CsvBindByName(required = true)
    private int capacity;

    @CsvBindAndSplitByName(
            column = "date_ranges",
            elementType = DateRange.class,
            splitOn = "\\|",
            converter = StringToDateRangeConverter.class,
            required = true
    )
    private List<AbstractDateRange> dateRanges;

    @CsvBindByName(column = "single_price", required = true)
    private double singlePrice;

    @CsvBindByName(column = "return_price")
    private double returnPrice;

    public TimeTravelServiceCsvRepr() {}

    public TimeTravelServiceCsvRepr(
            String id,
            String providerName,
            String timeMachine,
            int capacity,
            List<AbstractDateRange> dateRanges,
            double singlePrice,
            double returnPrice
    ) {
        this.id = id;
        this.providerName = providerName;
        this.timeMachine = timeMachine;
        this.capacity = capacity;
        this.dateRanges = dateRanges;
        this.singlePrice = singlePrice;
        this.returnPrice = returnPrice;
    }

    public TimeTravelServiceEntity toEntity(
            TimeMachineRepository machineRepository,
            TTSPRepository ttspRepository
    ) {
        // TODO: Check for both errors and throw if either is found
        TimeTravelServiceProviderEntity provider = ttspRepository.findByName(this.providerName);
        if (provider == null) {
            throw new IllegalArgumentException("Provider with name " + this.providerName + " does not exist");
        }

        String[] makeModel = timeMachine.split(" ", 2);
        TimeMachineEntity machine = machineRepository.findByMakeAndModel(makeModel[0], makeModel[1]);
        if (machine == null) {
            throw new IllegalArgumentException("Time machine with make " + makeModel[0] + " and model " +
                    makeModel[1] + " does not exist");
        }

        return new TimeTravelServiceEntity(
                this.id,
                provider,
                machine,
                this.capacity,
                this.dateRanges,
                this.singlePrice,
                this.returnPrice
        );
    }
}
