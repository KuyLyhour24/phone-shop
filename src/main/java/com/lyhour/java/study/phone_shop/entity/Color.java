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
@Table(name = "colors")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(name = "brands_id_generator", sequenceName = "brands_brand_id_seq", allocationSize = 1)
	@Column(name = "color_id")
	private Long id;

	@Column(name = "color_name")
	private String name;

}
