package com.example.timetravelbooks.ttsp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeMachineRepository extends MongoRepository<TimeMachineEntity, String> {

    TimeMachineEntity findByMakeAndModel(String make, String model);

}
