package com.lyhour.java.study.phone_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.study.phone_shop.dto.ColorDTO;
import com.lyhour.java.study.phone_shop.entity.Color;
import com.lyhour.java.study.phone_shop.mapper.BrandMapper;
import com.lyhour.java.study.phone_shop.mapper.ColorMapper;
import com.lyhour.java.study.phone_shop.repository.ColorRepository;
import com.lyhour.java.study.phone_shop.service.ColorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("colors")
@RequiredArgsConstructor
public class ColorController {
	private final ColorService colorService;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ColorDTO colorDTO) {
		Color color = ColorMapper.INSTANCE.toColor(colorDTO);
		color = colorService.create(color);
		
		return ResponseEntity.ok(ColorMapper.INSTANCE.toColorDTO(color));
	}
}
