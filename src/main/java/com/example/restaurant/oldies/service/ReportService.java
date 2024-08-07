package com.example.restaurant.oldies.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public interface ReportService {
    Resource generateReport(LocalDateTime startDateTime, LocalDateTime endDateTime, String format) throws IOException;
}
