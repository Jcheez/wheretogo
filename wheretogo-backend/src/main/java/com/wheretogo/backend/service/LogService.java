package com.wheretogo.backend.service;

import com.wheretogo.backend.models.Log;
import com.wheretogo.backend.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void addLog(Log log) {
        this.logRepository.insert(log);
    }

    public List<Log> findLogsByUser(long id) {
        return this.logRepository.findByChatId(id).orElseThrow(() -> new RuntimeException(
                String.format("Unable to find logs with unique id %s", id)
        ));
    }

    public List<Log> findAllLogs() {
        return this.logRepository.findAll();
    }

    public void deleteLogById(String id) {
        this.logRepository.deleteById(id);
    }
}
