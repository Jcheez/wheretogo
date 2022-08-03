package com.wheretogo.backend.service;

import com.wheretogo.backend.models.Attraction;
import com.wheretogo.backend.repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public void addAttraction(Attraction attraction) {
        this.attractionRepository.insert(attraction);
    }

    public List<Attraction> findAllAttractions() {
        return this.attractionRepository.findAll();
    }

    public Attraction findAttractionById(String id) {
        return this.attractionRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Unable to find Attraction with unique id %s", id)
        ));
    }

    public void deleteAttraction(String id) {
        this.attractionRepository.deleteById(id);
    }

    public void updateAttraction(Attraction att) {
        Attraction attraction = this.attractionRepository.findById(att.getId()).orElseThrow(() -> new RuntimeException(
                String.format("Unable to find Attraction with the unique id %s", att.getId())
        ));

        attraction.setLocations(att.getLocations());
        attraction.setDescription(att.getDescription());
        attraction.setReadMoreLink(att.getReadMoreLink());
        attraction.setCost(att.getCost());
        attraction.setType(att.getType());

        this.attractionRepository.save(attraction);
    }
}
