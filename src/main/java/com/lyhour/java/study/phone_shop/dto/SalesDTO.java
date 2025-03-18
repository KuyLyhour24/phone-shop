
package com.lyhour.java.study.phone_shop.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class SalesDTO {
	@NotEmpty(message="Product mmust not be null!")
	private List<ProductSoldDTO> products;
	
	@NotNull(message="Sold date can not be null!")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime soldDate;
	

}
