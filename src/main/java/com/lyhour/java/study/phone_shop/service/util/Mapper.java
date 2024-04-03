package com.lyhour.java.study.phone_shop.service.util;

import com.lyhour.java.study.phone_shop.dto.BrandDTO;
import com.lyhour.java.study.phone_shop.entity.Brand;


public class Mapper {
	//Logic to convert BrandDTO to Brand
	public static Brand toBrand(BrandDTO dto) {
		Brand brand = new Brand();
		//brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand;
	}
	//Logic to convert Brand to BrandDTO
	public static BrandDTO toBrandDTO(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setName(brand.getName());
		return brandDTO;
	}

}
