package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDto;
import com.devsuperior.dsmeta.dto.SummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportDto>> getReport(@RequestParam(name = "minDate", defaultValue = "") String start,
													 @RequestParam(name = "maxDate", defaultValue = "") String end,
													 @RequestParam(name = "name", defaultValue = "") String name,
													 Pageable pageable) {
		return ResponseEntity.ok(service.getReport(start, end, name, pageable));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDto>> getSummary(@RequestParam(name = "minDate", defaultValue = "") String start,
												 @RequestParam(name = "maxDate", defaultValue = "") String end) {
		return ResponseEntity.ok(service.getSummary(start, end));
	}
}
