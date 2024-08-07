package com.example.restaurant.oldies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class ReportDto {
    private LocalDateTime beginningReportTime;
    private LocalDateTime endingReportTime;
}