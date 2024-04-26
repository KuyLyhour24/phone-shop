package com.lyhour.java.study.phone_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lyhour.java.study.phone_shop.dto.ModelDTO;
import com.lyhour.java.study.phone_shop.entity.Model;
import com.lyhour.java.study.phone_shop.service.BrandService;
//Use Brand Service to call function getByID
@Mapper(componentModel = "spring" ,uses = BrandService.class)
public interface ModelMapper {
	
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
	@Mapping(target ="brand", source = "brandId")
	Model toModel(ModelDTO modeldto);
	@Mapping(target="brandId", source= "brand.id")
	ModelDTO toModelDTO (Model model);
	
//	default Brand tobrand(Integer brId) {
//		Brand brand = new Brand();
//		brand.setId(brId);
//		return brand;
//	}

}
