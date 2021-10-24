package com.dmartinc.poc.repositories;

import com.dmartinc.poc.repositories.model.PocMongoDocument;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import static com.dmartinc.poc.model.PocModelConstants.PROFILE_MONGO;

@Profile(PROFILE_MONGO)
@Repository
interface PocMongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<PocMongoDocument, String> {
}
