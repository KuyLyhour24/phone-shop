package com.lyhour.java.study.phone_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lyhour.java.study.phone_shop.dto.ColorDTO;
import com.lyhour.java.study.phone_shop.entity.Color;

@Mapper
public interface ColorMapper {
	ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

	Color toColor(ColorDTO colorDTO);

	ColorDTO toColorDTO(Color entity);
}
