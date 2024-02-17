package com.example.timetravelbooks.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    public String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public void configureConverters(MongoCustomConversions.MongoConverterConfigurationAdapter adapter) {
        adapter.registerConverter(LocalDateConverters.LocalDateReadConverter.INSTANCE);
        adapter.registerConverter(LocalDateConverters.LocalDateWriteConverter.INSTANCE);
    }
}