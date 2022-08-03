package com.wheretogo.backend.repository;

import com.wheretogo.backend.models.MockAttraction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MockAttractionRepository extends MongoRepository<MockAttraction, String> {
}
