package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDto;
import com.devsuperior.dsmeta.dto.SummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<ReportDto> getReport(String start, String end, String name) {

		if (end == null || end.equals("") ) {
			end = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).toString();
		}

		if (start == null || start.equals("")) {
			start = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L).toString();
		}
		return repository.getReport(LocalDate.parse(start), LocalDate.parse(end), name);
	}

	public List<SummaryDto> getSummary(String start, String end) {

		if (end == null || end.equals("") ) {
			end = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).toString();
		}

		if (start == null || start.equals("")) {
			start = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L).toString();
		}
		return repository.getSummary(LocalDate.parse(start), LocalDate.parse(end));
	}


}
