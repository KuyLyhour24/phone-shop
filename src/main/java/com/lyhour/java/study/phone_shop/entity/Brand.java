package com.lyhour.java.study.phone_shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Setter;

@Entity
@Setter
@Data
@Table(name = "brands")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brands_id_generator")
	@SequenceGenerator(name = "brands_id_generator", sequenceName = "brands_brand_id_seq", allocationSize = 1)
	@Column(name = "brand_id")
	private Long id;

	@Column(name = "brand_name")
	private String name;

}
