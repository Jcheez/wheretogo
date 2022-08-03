package com.wheretogo.backend.controller;

import com.wheretogo.backend.models.Attraction;
import com.wheretogo.backend.service.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {
    private final AttractionService attService;

    public AttractionController(AttractionService service) {
        this.attService = service;
    }

    @PostMapping
    public ResponseEntity addAttraction(@RequestBody Attraction attraction) {
        this.attService.addAttraction(attraction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attraction> findAttraction(@PathVariable String id) {
        return ResponseEntity.ok(this.attService.findAttractionById(id));
    }

    @GetMapping
    public ResponseEntity<List<Attraction>> findAllAttractions() {
        return ResponseEntity.ok(this.attService.findAllAttractions());
    }

    @PutMapping
    public ResponseEntity updateAttraction(@RequestBody Attraction newAttraction) {
        this.attService.updateAttraction(newAttraction);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAttraction(@PathVariable String id) {
        this.attService.deleteAttraction(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
