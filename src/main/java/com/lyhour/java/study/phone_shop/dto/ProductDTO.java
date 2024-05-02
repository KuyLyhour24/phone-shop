package com.lyhour.java.study.phone_shop.dto;

import com.lyhour.java.study.phone_shop.entity.Color;
import com.lyhour.java.study.phone_shop.entity.Model;

import lombok.Data;

@Data
public class ProductDTO {
	private Long modelId;
	private Long colorId;

}
