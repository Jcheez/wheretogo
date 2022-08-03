package com.wheretogo.backend.repository;

import com.wheretogo.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'chatId': ?0}")
    Optional<User> findByChatId(long chatId);

}
