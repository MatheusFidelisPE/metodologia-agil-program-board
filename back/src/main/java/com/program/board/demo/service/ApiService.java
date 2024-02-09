package com.program.board.demo.service;

import com.program.board.demo.model.Epic;
import com.program.board.demo.repository.FeatureRepository;
import com.program.board.demo.repository.EpicRepository;
import com.program.board.demo.model.Feature;
import com.program.board.demo.model.dtos.FeatureDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

// Regras de negócio
@Service
public class ApiService {
    private FeatureRepository featureRepository;
    private EpicRepository epicRepository;

    public List<Epic> getEpics(){
        return epicRepository.findAll();
    }

    public Object getEpicById(Long id){
        Optional<Epic> epic = epicRepository.findById(id);
        if(epic.isEmpty()) {
            return "Epic not found.";
        }
        return epic.get();
    }

    public Feature saveFeature(FeatureDto featureDto) {
        var featureModel = new Feature();
        BeanUtils.copyProperties(featureDto, featureModel);
        return featureRepository.save(featureModel);
    }

    public Object updateFeature(Long id, FeatureDto featureDto) {
        Optional<Feature> feature = featureRepository.findById(id);
        if(feature.isEmpty()) {
            return "Feature not found.";
        }
        var featureModel = feature.get();
        BeanUtils.copyProperties(featureDto, featureModel);
        return featureRepository.save(featureModel);
    }

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }
    public Object getFeatureById(Long id) {
        Optional<Feature> feature = featureRepository.findById(id);
        if(feature.isEmpty()) {
            return "Feature not found.";
        }
        return feature.get();
    }
}
