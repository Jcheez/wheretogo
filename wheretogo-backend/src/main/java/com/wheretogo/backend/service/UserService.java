package com.wheretogo.backend.service;

import com.wheretogo.backend.enums.AttractionTypes;
import com.wheretogo.backend.models.Attraction;
import com.wheretogo.backend.models.User;
import com.wheretogo.backend.repository.AttractionRepository;
import com.wheretogo.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;

    public UserService(UserRepository userRepo, AttractionRepository attRepo) {
        this.userRepository = userRepo;
        this.attractionRepository = attRepo;
    }

    public void addUser(User user) {
        user.setOptions(this.tidyAttractions());
        this.userRepository.insert(user);
    }

    public User findUser(long chatId) {
        return this.userRepository.findByChatId(chatId).orElseThrow(() -> new RuntimeException(
                String.format("Unable to find User with the chatId: %s", chatId)
        ));
    }

    public void updateLocations(long chatId) {
        User user = this.userRepository.findByChatId(chatId).orElseThrow(() -> new RuntimeException(
                String.format("Unable to find user with the chatId: %s", chatId)
        ));

        user.setOptions(this.tidyAttractions());
        this.userRepository.save(user);
    }

    public List<String> getLocations(long chatId, AttractionTypes type, int numRecommendations) {
        User user = this.userRepository.findByChatId(chatId).orElseThrow(() -> new RuntimeException(
                String.format("Unable to find user with the chatId: %s", chatId)
        ));
        List<String> attIds = new ArrayList<>();
        List<String> listAtt = user.getOptions().get(type);
        if (listAtt.size() <= numRecommendations) {
            attIds = listAtt;
            user.getOptions().put(type, new ArrayList<>());
            this.userRepository.save(user);
        } else {
            for (int i=0; i<numRecommendations; i++) {
                String attId = listAtt.remove(listAtt.size() - 1);
                attIds.add(attId);
            }
        }
        return attIds;
    }

    private HashMap<AttractionTypes, List<String>> tidyAttractions() {
        HashMap<AttractionTypes, List<String>> tidiedAttractions = new HashMap<>();
        List<Attraction> attractions = this.attractionRepository.findAll();
        Collections.shuffle(attractions);
        for (Attraction att : attractions) {
            if (tidiedAttractions.containsKey(att.getType())) {
                tidiedAttractions.get(att.getType()).add(att.getId());
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(att.getId());
                tidiedAttractions.put(att.getType(), newList);
            }
        }
        return tidiedAttractions;
    }
}
