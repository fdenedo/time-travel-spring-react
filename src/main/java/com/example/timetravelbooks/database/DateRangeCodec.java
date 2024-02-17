package com.example.timetravelbooks.database;

import com.example.timetravelbooks.model.AbstractDateRange;
import com.example.timetravelbooks.model.DateRange;
import com.example.timetravelbooks.model.DateRangeFromPresent;
import com.example.timetravelbooks.model.DateRangeToPresent;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.time.LocalDate;

public class DateRangeCodec implements Codec<AbstractDateRange> {

    @Override
    public AbstractDateRange decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartArray();
        String startDate = reader.readString();
        String endDate = reader.readString();
        reader.readEndArray();

        if ("PRESENT".equals(startDate)) {
            return new DateRangeFromPresent(LocalDate.parse(endDate));
        } else if ("PRESENT".equals(endDate)) {
            return new DateRangeToPresent(LocalDate.parse(startDate));
        } else {
            return new DateRange(LocalDate.parse(startDate), LocalDate.parse(endDate));
        }
    }

    @Override
    public void encode(BsonWriter writer, AbstractDateRange dateRange, EncoderContext encoderContext) {
        writer.writeStartArray();
        if (dateRange instanceof DateRangeFromPresent) {
            writer.writeString("PRESENT");
            writer.writeString(dateRange.getEndDate().toString());
        } else if (dateRange instanceof DateRangeToPresent) {
            writer.writeString(dateRange.getStartDate().toString());
            writer.writeString("PRESENT");
        } else {
            writer.writeString(dateRange.getStartDate().toString());
            writer.writeString(dateRange.getEndDate().toString());
        }
        writer.writeEndArray();
    }

    @Override
    public Class<AbstractDateRange> getEncoderClass() {
        return AbstractDateRange.class;
    }
}
