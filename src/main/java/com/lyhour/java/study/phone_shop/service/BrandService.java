package com.lyhour.java.study.phone_shop.service;

import java.util.List;

import com.lyhour.java.study.phone_shop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer brandId);
	Brand update(Brand brandUpdate, Integer id);
	List<Brand> getBrand();
	List<Brand> getBrand(String name);
}
