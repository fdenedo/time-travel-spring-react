package com.example.timetravelbooks.ttsp;

import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;

public class TTSPCsvRepr {

    @CsvBindByName(required = true)
    private String id;

    @CsvBindByName(column = "name", required = true)
    private String name;

    @CsvBindByName(column = "description", required = true)
    private String description;

    public TTSPCsvRepr() {}

    public TTSPCsvRepr(
            String id,
            String name,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TimeTravelServiceProviderEntity toEntity() {
        // TODO: Verification of Providers (no repeat names)
        return new TimeTravelServiceProviderEntity(
                this.id,
                this.name,
                this.description,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

}
