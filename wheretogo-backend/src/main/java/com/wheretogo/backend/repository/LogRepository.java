package com.wheretogo.backend.repository;

import com.wheretogo.backend.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LogRepository extends MongoRepository<Log, String> {

    @Query("{'reqId': ?0}")
    Optional<List<Log>> findByChatId(long chatId);
}
