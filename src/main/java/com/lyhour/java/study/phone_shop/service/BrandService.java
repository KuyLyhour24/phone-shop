package com.lyhour.java.study.phone_shop.service;

import com.lyhour.java.study.phone_shop.entity.Brand;

import lombok.Data;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer brandId);
	Brand update(Brand brandUpdate, Integer id);
}
