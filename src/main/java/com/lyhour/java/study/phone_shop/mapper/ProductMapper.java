package com.lyhour.java.study.phone_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lyhour.java.study.phone_shop.dto.ProductDTO;
import com.lyhour.java.study.phone_shop.dto.ProductImportDTO;
import com.lyhour.java.study.phone_shop.entity.Product;
import com.lyhour.java.study.phone_shop.entity.ProductImportHistory;
import com.lyhour.java.study.phone_shop.service.ColorService;
import com.lyhour.java.study.phone_shop.service.ModelService;

@Mapper(componentModel = "spring", uses = {ModelService.class,ColorService.class})
public interface ProductMapper {
	
	@Mapping(target="model", source ="modelId")
	@Mapping(target= "color" , source="colorId")
	Product toProduct(ProductDTO productDTO);
	

	@Mapping(target="id" ,ignore = true)
	ProductImportHistory toImportHistory(ProductImportDTO importDTO, Product product);

}
