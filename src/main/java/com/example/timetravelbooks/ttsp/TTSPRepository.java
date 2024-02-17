package com.example.timetravelbooks.ttsp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TTSPRepository extends MongoRepository<TimeTravelServiceProviderEntity, String> {

    TimeTravelServiceProviderEntity findByName(String name);

}
