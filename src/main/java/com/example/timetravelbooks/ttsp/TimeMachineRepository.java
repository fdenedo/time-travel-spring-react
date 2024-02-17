package com.example.timetravelbooks.ttsp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeMachineRepository extends MongoRepository<TimeMachineEntity, String> {

    List<TimeMachineEntity> findByMakeAndModel(String make, String model);

}
