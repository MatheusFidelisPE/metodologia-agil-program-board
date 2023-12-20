package com.programboard.programboard.controllers;

import com.programboard.programboard.dtos.FeatureRecordDto;
import com.programboard.programboard.models.FeatureModel;
import com.programboard.programboard.repositories.FeatureRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FeatureController {

    @Autowired
    FeatureRepository featureRepository;

    @PostMapping("/program-board/features")
    public ResponseEntity<FeatureModel> saveFeature(@RequestBody @Valid FeatureRecordDto featureRecordDto) {
        var featureModel = new FeatureModel();
        BeanUtils.copyProperties(featureRecordDto, featureModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(featureRepository.save(featureModel));
    }

    @PutMapping("/program-board/features/{id}")
    public ResponseEntity<Object> updateFeature(@PathVariable(value = "id") UUID id, @RequestBody @Valid FeatureRecordDto featureRecordDto) {
        Optional<FeatureModel> feature = featureRepository.findById(id);
        if(feature.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feature not found.");
        }
        var featureModel = feature.get();
        BeanUtils.copyProperties(featureRecordDto, featureModel);
        return ResponseEntity.status(HttpStatus.OK).body(featureRepository.save(featureModel));
    }

    @GetMapping("/program-board/features")
    ResponseEntity<List<FeatureModel>> getAllFeatures() {
        List<FeatureModel> featuresList = featureRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(featuresList);
    }
}
