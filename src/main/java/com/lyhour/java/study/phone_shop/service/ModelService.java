package com.lyhour.java.study.phone_shop.service;

import java.util.List;

import com.lyhour.java.study.phone_shop.entity.Model;

public interface ModelService {
   Model save(Model model);
   List<Model> getModelByBrandId(Long id);
   Model getById(Long Id);
}
