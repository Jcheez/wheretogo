package com.wheretogo.backend.controller;

import com.wheretogo.backend.models.Log;
import com.wheretogo.backend.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity addLog(@RequestBody Log log) {
        this.logService.addLog(log);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Log>> findLogsByUser(@PathVariable long id) {
        return ResponseEntity.ok(this.logService.findLogsByUser(id));
    }

    @GetMapping
    public ResponseEntity<List<Log>> findAllLogs() {
        return ResponseEntity.ok(this.logService.findAllLogs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLogs(@PathVariable String id) {
        this.logService.deleteLogById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
