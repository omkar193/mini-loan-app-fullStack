package com.techdome.miniLoanApp.TestServices;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mongodb.client.MongoClient;
import com.techdome.miniLoanApp.service.MongoDBService;

@ExtendWith(MockitoExtension.class)
public class MongoDBServiceTest {

    @Mock
    private MongoClient mongoClient;

    @InjectMocks
    private MongoDBService mongoDBService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCleanup() {
        mongoDBService.cleanup();
        verify(mongoClient, times(1)).close();
    }

    @Test
    void testCleanupWhenMongoClientIsNull() {
        mongoDBService = new MongoDBService(null);
        mongoDBService.cleanup();
        verify(mongoClient, never()).close();
    }
}

