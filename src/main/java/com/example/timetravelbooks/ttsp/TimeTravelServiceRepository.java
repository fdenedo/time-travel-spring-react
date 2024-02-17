package com.example.timetravelbooks.ttsp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TimeTravelServiceRepository extends MongoRepository<TimeTravelServiceEntity, String> {

    List<TimeTravelServiceSummary> findAllProjectedBy();

    /**
     * Query to find time travel services in the database with a date range that supports the given arrival date (where
     * dateRange.startDate <= arrivalTime <= dateRange.endDate) and a capacity greater than or equal to passengers.
     *
     * @param arrivalDate   the requested arrival date of the journey
     * @param passengers    the number of passengers to travel
     * @return
     */
    @Query("{$and:[{date_ranges: {$elemMatch: {startDate: {$lte: ?0}, endDate: {$gte: ?0}}}}, {capacity: {$gte: ?1}}]}")
    List<TimeTravelServiceSummary> findByDateRangeAndCapacity(LocalDate arrivalDate, int passengers);

}
