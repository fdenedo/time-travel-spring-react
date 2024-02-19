package com.example.timetravelbooks.database;

import com.example.timetravelbooks.model.AbstractDateRange;
import com.example.timetravelbooks.model.DateRange;
import com.example.timetravelbooks.model.DateRangeFromPresent;
import com.example.timetravelbooks.model.DateRangeToPresent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class DateRangeConverters {

    private static final String PRESENT = "PRESENT";

    @WritingConverter
    public enum DateRangeToStringConverter implements Converter<AbstractDateRange, String> {
        INSTANCE;

        @NonNull
        @Override
        public String convert(AbstractDateRange source) {
            String startDate = source instanceof DateRangeFromPresent ? PRESENT : source.getStartDate().toString();
            String endDate = source instanceof DateRangeToPresent ? PRESENT : source.getEndDate().toString();

            return String.join(":", startDate, endDate);
        }
    }

    @ReadingConverter
    public enum StringToDateRangeConverter implements Converter<String, AbstractDateRange> {
        INSTANCE;

        @NonNull
        @Override
        public AbstractDateRange convert(String source) {
            String[] split = source.split(":");
            if (PRESENT.equals(split[0])) return new DateRangeFromPresent(LocalDate.parse(split[1]));
            if (PRESENT.equals(split[1])) return new DateRangeToPresent(LocalDate.parse(split[0]));

            return new DateRange(LocalDate.parse(split[0]), LocalDate.parse(split[1]));
        }
    }
}
