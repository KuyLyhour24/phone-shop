package com.lyhour.java.study.phone_shop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productImportHistories")
public class ProductImportHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="import_id")
	private Long id;
	
	@Column(name="import_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime importDate;
	
	@Column(name="import_unit")
	private Integer importUnit;
	
	@Column(name="price_per_unit")
	private BigDecimal pricePerUnit;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
}
