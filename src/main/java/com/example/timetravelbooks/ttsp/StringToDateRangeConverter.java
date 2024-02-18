package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.database.DateRangeConverters;
import com.opencsv.bean.AbstractCsvConverter;

public class StringToDateRangeConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String s) {
        String[] split = s.split(":", 2);
        return DateRangeConverters.StringArrayToDateRangeConverter.INSTANCE.convert(split);
    }

}
