package com.lyhour.java.study.phone_shop.spec;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductImportHistoriesFilter {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
