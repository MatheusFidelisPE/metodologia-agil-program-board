package com.program.board.demo.service;

import com.program.board.demo.map.Mapping;
import com.program.board.demo.model.Epic;
import com.program.board.demo.repository.FeatureRepository;
import com.program.board.demo.repository.EpicRepository;
import com.program.board.demo.model.Feature;
import com.program.board.demo.model.dtos.FeatureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

// Regras de negócio
@Service
public class ApiService {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private EpicRepository epicRepository;

    @Autowired
    private Mapping map;
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

    public FeatureDto saveFeature(FeatureDto featureDto) {
        var featureModel = new Feature();
        BeanUtils.copyProperties(featureDto, featureModel);
        featureRepository.save(featureModel);
        return map.featureDto(featureModel);
    }

    public Object updateFeature(FeatureDto featureDto) {
        Optional<Feature> feature = featureRepository.findById(featureDto.getIdFeature());
        if(feature.isEmpty()) {
            return "NOT FOUND";
        }
        var featureModel = feature.get();
        BeanUtils.copyProperties(featureDto, featureModel);

        return featureRepository.save(featureModel);
    }

    public List<FeatureDto> getAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        List<FeatureDto> dtos = map.featureDtos(features);
        return dtos;
    }
    public FeatureDto getFeatureById(Long id) {
        Optional<Feature> feature = featureRepository.findById(id);
        if(feature.isEmpty()) {
            return new FeatureDto();
        }
        return map.featureDto(feature.get());
    }
    public FeatureDto deleteFeature(Long id ){
        Feature ft = featureRepository.findById(id).get();
        featureRepository.deleteById(id);
        return map.featureDto(ft);
    }

    public FeatureDto alterarSprintDeFeature(Long idsprint, Long idfeature) {
        Feature ft = featureRepository.findById(idfeature).get();
        Sprint spt = sprintRepository.findById(idsprint).get();

        ft.setSprint(spt);
        featureRepository.save(ft);
        return map.featureDto(ft);
    }
}
