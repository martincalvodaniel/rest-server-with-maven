package com.dmartinc.poc.configurations.mongo;

import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.dmartinc.poc.model.PocModelConstants.PROFILE_MONGO;

@Profile(PROFILE_MONGO)
@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class CustomMongoDataAutoConfiguration extends MongoDataAutoConfiguration {

}
