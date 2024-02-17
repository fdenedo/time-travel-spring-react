package com.example.timetravelbooks.ttsp;

import com.example.timetravelbooks.model.DateRange;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface TimeTravelServiceSummary {

    String getId();

    @Value("#{target.provider.name}")
    String getProviderName();

    @Value("#{target.timeMachine.make + ' ' + target.timeMachine.model}")
    String getTimeMachineMakeAndModel();

    List<DateRange> getDateRanges();

    double getSinglePrice();

    double getReturnPrice();

}
