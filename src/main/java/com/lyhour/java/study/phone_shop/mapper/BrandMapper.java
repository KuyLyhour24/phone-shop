package com.lyhour.java.study.phone_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lyhour.java.study.phone_shop.dto.BrandDTO;
import com.lyhour.java.study.phone_shop.entity.Brand;

@Mapper
public interface BrandMapper {
BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	Brand toBrand(BrandDTO dto);
	BrandDTO toBrandDTO(Brand entity);
}
