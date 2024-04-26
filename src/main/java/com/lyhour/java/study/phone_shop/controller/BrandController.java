package com.lyhour.java.study.phone_shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.study.phone_shop.dto.BrandDTO;
import com.lyhour.java.study.phone_shop.dto.ModelDTO;
import com.lyhour.java.study.phone_shop.dto.PageDTO;
import com.lyhour.java.study.phone_shop.entity.Brand;
import com.lyhour.java.study.phone_shop.entity.Model;
import com.lyhour.java.study.phone_shop.mapper.BrandMapper;
import com.lyhour.java.study.phone_shop.mapper.ModelEntityMapper;
import com.lyhour.java.study.phone_shop.service.BrandService;
import com.lyhour.java.study.phone_shop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("brands")
public class BrandController {
	
	private final BrandService brandService;
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	
	}
	@PutMapping("{id}")
	public ResponseEntity<?> update (@PathVariable("id") Integer brandId,@RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updateBrand = brandService.update(brand, brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updateBrand));
	}
	
	
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		
		Page<Brand> page = brandService.getBrands(params);
		PageDTO pageDTO = new PageDTO(page);
		/*
		List<BrandDTO> list = brandService.getBrands(params)
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());
		*/
		return ResponseEntity.ok(pageDTO);
	}
	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelByBrandId(@PathVariable("id") Integer brandId){
		List<Model> brand = modelService.getModelByBrandId(brandId);
		List<ModelDTO> list = brand.stream()
		.map(modelMapper::toModelDTO) //Method Reference or we can use .map(model -> modelMapper.toModelDTO(model))
		.toList();
		return ResponseEntity.ok(list);
	
	}
	
	
}
