package com.wheretogo.backend.controller;

import com.wheretogo.backend.models.MockAttraction;
import com.wheretogo.backend.service.MockAttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mock/attractions")
public class MockAttractionController {

    private final MockAttractionService mockAttractionService;

    public MockAttractionController(MockAttractionService mockAttractionService) {
        this.mockAttractionService = mockAttractionService;
    }

    @PostMapping
    public ResponseEntity addMockAttraction(@RequestBody MockAttraction mockAttraction) {
        this.mockAttractionService.addMockAttraction(mockAttraction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<MockAttraction>> findAllMockAttractions() {
        return ResponseEntity.ok(this.mockAttractionService.findAllMockAttractions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMockAttraction(@PathVariable String id) {
        this.mockAttractionService.deleteMockAttraction(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
