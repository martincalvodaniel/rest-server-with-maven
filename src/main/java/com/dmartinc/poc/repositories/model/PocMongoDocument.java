package com.dmartinc.poc.repositories.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class PocMongoDocument {

    @Id
    private ObjectId id;

    private Date timestamp;

    private String content;
}
