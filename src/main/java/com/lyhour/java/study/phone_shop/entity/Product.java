package com.lyhour.java.study.phone_shop.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.Setter;

@Entity
@Setter
@Data
@Table(name = "products",uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name = "brands_id_generator", sequenceName = "brands_brand_id_seq", allocationSize = 1)
	@Column(name = "broduct_id")
	private Long id;

	@Column(name = "product_name")
	private String name;
	
	@Column(name="available_unit")
	private Integer availableUnit;
	
	@Column(name="image_path")
	private String imagePath;
	
	@Column(name="sale_price")
	private BigDecimal salePrice;
	
	@ManyToOne
	@JoinColumn(name="model_id")
	private Model model;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	
	

}
