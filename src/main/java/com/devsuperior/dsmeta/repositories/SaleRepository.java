package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.ReportDto(obj.id, obj.date, obj.amount, obj.seller.name) FROM Sale obj WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) AND obj.date BETWEEN :start AND :end")
    List<ReportDto> getReport(String start, String end, String name);

}
