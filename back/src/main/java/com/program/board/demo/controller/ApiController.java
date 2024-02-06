package com.program.board.demo.controller;

import com.program.board.demo.model.Feature;
import com.program.board.demo.model.dtos.FeatureDto;
import com.program.board.demo.model.dtos.TeamDto;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Endpoints da API
@RestController
@RequestMapping("/api-v1")
public class ApiController {

    @PostMapping("/feature/create-feature")
    public ResponseEntity<?> saveFeature(@RequestBody FeatureDto featureDto) {
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/feature/update-feature")
    public ResponseEntity<?> updateFeature(@RequestBody FeatureDto featureDto) {

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/feature")
    ResponseEntity<?> getAllFeatures() {
        List<Feature> featuresList = null;

        return ResponseEntity.ok("ok");
    }
    @GetMapping("/feature/{id}")
    ResponseEntity<?> getOneFeature(@PathVariable(value = "id") Long id) {

        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("/feature/delete-feature/{id}")
    ResponseEntity<?> deleteFeature(@PathVariable(value = "id") Long id) {

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/team/create-team")
    ResponseEntity<?> createTeam(@RequestBody TeamDto team){
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/team")
    ResponseEntity<?> getTeams(){

        return ResponseEntity.ok("ok");
    }
    @GetMapping("/team/{id}")
    ResponseEntity<?> getTeamById(@PathVariable Long id){

        return ResponseEntity.ok("ok");
    }
    @PutMapping("/team/alter-team")
    public ResponseEntity<?> getTeams(@RequestBody TeamDto team){

        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("/team/delete-team/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        return ResponseEntity.ok(HttpStatus.resolve(200));
    }

}
