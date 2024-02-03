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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        if(!featuresList.isEmpty()) {
            for(FeatureModel featureModel : featuresList) {
                UUID id = featureModel.getIdFeature();
                featureModel.add(linkTo(methodOn(FeatureController.class).getOneFeature(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(featuresList);
    }
    @GetMapping("/program-board/features/{id}")
    ResponseEntity<Object> getOneFeature(@PathVariable(value = "id") UUID id) {
        Optional<FeatureModel> feature = featureRepository.findById(id);
        if(feature.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feature not found.");
        }
        feature.get().add(linkTo(methodOn(FeatureController.class).getAllFeatures()).withRel("Features List"));
        return ResponseEntity.status(HttpStatus.OK).body(feature.get());
    }
}
