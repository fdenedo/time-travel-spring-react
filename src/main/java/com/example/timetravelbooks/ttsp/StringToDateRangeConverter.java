package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.database.DateRangeConverters;
import com.opencsv.bean.AbstractCsvConverter;

public class StringToDateRangeConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String s) {
        return DateRangeConverters.StringToDateRangeConverter.INSTANCE.convert(s);
    }

}
