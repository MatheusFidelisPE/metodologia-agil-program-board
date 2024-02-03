package com.programboard.programboard.controller;

import com.programboard.programboard.model.EpicModel;
import com.programboard.programboard.repository.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/epic")
public class EpicController {

    @Autowired
    private EpicRepository epicRepository;

    @GetMapping(value = "/get-epics")
    public ResponseEntity<?> getEpics(){
        List<?> epics = epicRepository.findAll();
        return ResponseEntity.ok(epics);
    }

    @GetMapping(value = "/get-epic/{id}")
    public ResponseEntity<?> getEpicById(@PathVariable Long id){
        Optional<EpicModel> epic = epicRepository.findEpicModelById(id);
        return ResponseEntity.ok(epic);
    }
}
