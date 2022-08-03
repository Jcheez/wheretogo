package com.wheretogo.backend.service;

import com.wheretogo.backend.models.MockAttraction;
import com.wheretogo.backend.repository.MockAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockAttractionService {

    private final MockAttractionRepository mockAttractionRepository;

    public MockAttractionService(MockAttractionRepository mockAttractionRepository) {
        this.mockAttractionRepository = mockAttractionRepository;
    }

    public void addMockAttraction(MockAttraction mockAttraction) {
        this.mockAttractionRepository.insert(mockAttraction);
    }

    public List<MockAttraction> findAllMockAttractions() {
        return this.mockAttractionRepository.findAll();
    }

    public void deleteMockAttraction(String id) {
        this.mockAttractionRepository.deleteById(id);
    }
}
