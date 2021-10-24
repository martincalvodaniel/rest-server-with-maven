package com.dmartinc.poc.configurations.mongo;

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.dmartinc.poc.model.PocModelConstants.PROFILE_MONGO;

@Profile(PROFILE_MONGO)
@Configuration
public class CustomMongoAutoConfiguration extends MongoAutoConfiguration {
}