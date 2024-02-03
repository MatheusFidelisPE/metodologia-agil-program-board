package com.programboard.programboard.controller;

import com.programboard.programboard.dtos.FeatureRecordDto;
import com.programboard.programboard.model.FeatureModel;
import com.programboard.programboard.repository.FeatureRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeatureController {

    @Autowired
    FeatureRepository featureRepository;

    @PostMapping("/programboard/features")
    public ResponseEntity<FeatureModel> saveFeature(@RequestBody @Valid FeatureRecordDto featureRecordDto);
    var featureModel = new FeatureModel();
    BeanUtils.copyProperties(featureRecordDto, featureModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(featureRepository.save(featureModel));

}
