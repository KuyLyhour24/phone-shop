package com.lyhour.java.study.phone_shop.service.impl;

import org.springframework.stereotype.Service;

import com.lyhour.java.study.phone_shop.entity.Color;
import com.lyhour.java.study.phone_shop.exception.ResourceNotFoundException;
import com.lyhour.java.study.phone_shop.repository.ColorRepository;
import com.lyhour.java.study.phone_shop.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService{

	private final ColorRepository colorRepository;
	
	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Color", id));
	}

	@Override
	public Color create(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

}
