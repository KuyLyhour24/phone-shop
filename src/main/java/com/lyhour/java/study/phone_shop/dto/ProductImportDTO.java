package com.lyhour.java.study.phone_shop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class ProductImportDTO {
	@NotNull(message="Product Id can not be null!")
	private Long productId;
	
	@NotNull(message="Import date can not be null!")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime importDate;
	
	@Min(value = 1, message="Import unit must be greater then 0!")
	private Integer importUnit;
	
	@DecimalMin(value="0.00001" , message ="Price can not be null!")
	private BigDecimal pricePerUnit;

	
}
