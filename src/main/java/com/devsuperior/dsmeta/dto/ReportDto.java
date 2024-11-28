package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public record ReportDto(Long id, LocalDate date, Double amount, String sellerName) {
}
