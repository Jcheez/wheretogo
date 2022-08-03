package com.wheretogo.backend.repository;

import com.wheretogo.backend.models.Attraction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AttractionRepository extends MongoRepository<Attraction, String> {
}