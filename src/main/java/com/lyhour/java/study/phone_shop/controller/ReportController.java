package com.lyhour.java.study.phone_shop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.study.phone_shop.dto.ProductReportDTO;
import com.lyhour.java.study.phone_shop.projection.ProductSold;
import com.lyhour.java.study.phone_shop.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
public class ReportController {
	private final ReportService reportService;
//	@GetMapping("{startDate}/{endDate}")
//	public ResponseEntity<?> reportProduct(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("startDate") LocalDateTime startDate,
//			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("endDate") LocalDateTime endDate){
//		List<ProductSold> productReport = reportService.getProductReport(startDate, endDate);
//		return ResponseEntity.ok(productReport);
//	}
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> productReport(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("startDate") LocalDateTime startDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("endDate") LocalDateTime endDate){
		List<ProductReportDTO> productReport = reportService.getProductReports(startDate, endDate);
		return ResponseEntity.ok(productReport);
	}

}
