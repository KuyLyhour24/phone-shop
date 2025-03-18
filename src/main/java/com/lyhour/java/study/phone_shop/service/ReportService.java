package com.lyhour.java.study.phone_shop.service;

import java.time.LocalDateTime;
import java.util.List;

import com.lyhour.java.study.phone_shop.dto.ProductReportDTO;
import com.lyhour.java.study.phone_shop.projection.ProductSold;

public interface ReportService {
	List<ProductSold> getProductReport(LocalDateTime startDate, LocalDateTime endDate);
	List<ProductReportDTO> getProductReports(LocalDateTime startDate, LocalDateTime endDate);

}
