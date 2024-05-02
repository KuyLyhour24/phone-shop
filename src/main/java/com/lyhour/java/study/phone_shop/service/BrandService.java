package com.lyhour.java.study.phone_shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.lyhour.java.study.phone_shop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long brandId);
	Brand update(Brand brandUpdate, Long id);
	
	List<Brand> getBrands(String name);
	//List<Brand> getBrands(Map<String,String> params);
	
	Page<Brand> getBrands(Map<String, String> params);
}
