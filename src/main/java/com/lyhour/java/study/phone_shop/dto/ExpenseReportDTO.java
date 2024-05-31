package com.lyhour.java.study.phone_shop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseReportDTO {
	
	private Long productId;
	private String productName;
	private Integer totalUnit;
	private BigDecimal totalAmount;
	

}
