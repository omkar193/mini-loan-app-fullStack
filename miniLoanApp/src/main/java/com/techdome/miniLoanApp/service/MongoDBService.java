package com.techdome.miniLoanApp.service;


import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;

@Service
public class MongoDBService {

    private final MongoClient mongoClient;

    @Autowired
    public MongoDBService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @PreDestroy
    public void cleanup() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
