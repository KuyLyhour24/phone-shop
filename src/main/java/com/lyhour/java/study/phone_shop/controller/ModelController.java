package com.lyhour.java.study.phone_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.java.study.phone_shop.dto.ModelDTO;
import com.lyhour.java.study.phone_shop.entity.Model;
import com.lyhour.java.study.phone_shop.mapper.ModelMapper;
import com.lyhour.java.study.phone_shop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {

	private final ModelService modelService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDto) {
		Model model = modelMapper.toModel(modelDto);
		model = modelService.save(model);
		return ResponseEntity.ok(model);
	}

}
