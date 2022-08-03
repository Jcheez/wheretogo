package com.wheretogo.backend.repository;

import com.wheretogo.backend.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
