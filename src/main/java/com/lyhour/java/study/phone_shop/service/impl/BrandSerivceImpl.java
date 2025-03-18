package com.lyhour.java.study.phone_shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lyhour.java.study.phone_shop.entity.Brand;
import com.lyhour.java.study.phone_shop.exception.ResourceNotFoundException;
import com.lyhour.java.study.phone_shop.repository.BrandRepository;
import com.lyhour.java.study.phone_shop.service.BrandService;
import com.lyhour.java.study.phone_shop.service.util.PageUtil;
import com.lyhour.java.study.phone_shop.spec.BrandFilter;
import com.lyhour.java.study.phone_shop.spec.BrandSpec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandSerivceImpl implements BrandService {
	@Autowired
	private final BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Integer id) {
		return brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand", id));

	}

	@Override
	public Brand update(Brand brandUpdate, Integer id) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}

	

	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContaining(name);
	}
	/*
	@Override
	public List<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
			
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
				
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		return brandRepository.findAll(brandSpec);
		
	}
	*/

	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		
		if(params.containsKey("name")) {
			String name  = params.get("name");
			brandFilter.setName(name);
		}
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
		
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit= Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if (params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		BrandSpec brandSpec= new BrandSpec(brandFilter);
		//Pageable pageable = BrandPageable.pageable(params);
		Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
		Page<Brand> page = brandRepository.findAll(brandSpec,pageable);
		return page;
	}

}