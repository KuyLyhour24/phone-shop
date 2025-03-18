package com.lyhour.java.study.phone_shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lyhour.java.study.phone_shop.dto.ModelDTO;
import com.lyhour.java.study.phone_shop.entity.Model;
import com.lyhour.java.study.phone_shop.mapper.ModelEntityMapper;
import com.lyhour.java.study.phone_shop.repository.ModelRepository;
import com.lyhour.java.study.phone_shop.service.ModelService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService{
	
	private final ModelRepository modelRepository;
	

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}


	@Override
	public List<Model> getModelByBrandId(Integer id) {
		
		return modelRepository.findModelByBrandId(id);
	}

}
