package com.program.board.demo.controller;

import com.program.board.demo.model.Feature;
import com.program.board.demo.model.dtos.FeatureDto;
import com.program.board.demo.model.dtos.TaskDto;
import com.program.board.demo.model.dtos.TeamDto;
import com.program.board.demo.service.ApiService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskDecorator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Endpoints da API
@RestController
@RequestMapping("/api-v1")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping(value = "/feature/create-feature", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFeature(@RequestBody FeatureDto featureDto) {
        apiService.saveFeature(featureDto);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/feature/update-feature")
    public ResponseEntity<?> updateFeature(@RequestBody FeatureDto featureDto) {
        apiService.updateFeature(featureDto);
        return ResponseEntity.ok("");
    }

    @GetMapping("/feature")
    ResponseEntity<?> getAllFeatures() {
        List<FeatureDto> featuresList = apiService.getAllFeatures();
        return ResponseEntity.ok(featuresList);
    }
    @GetMapping("/feature/{id}")
    ResponseEntity<?> getOneFeature(@PathVariable(value = "id") Long id) {
        FeatureDto dto = apiService.getFeatureById(id);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/feature/delete-feature/{id}")
    ResponseEntity<?> deleteFeature(@PathVariable(value = "id") Long id) {
        FeatureDto dto = apiService.deleteFeature(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/team/create-team", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createTeam(@RequestBody TeamDto team){
        return ResponseEntity.ok(apiService.saveTeam(team));
    }
    @GetMapping("/team")
    ResponseEntity<?> getTeams(){
        return ResponseEntity.ok(apiService.getAllTeams());
    }
    @GetMapping("/team/{id}")
    ResponseEntity<?> getTeamById(@PathVariable Long id){
        return ResponseEntity.ok(apiService.getTeamById(id));
    }
    @PutMapping("/team/alter-team")
    public ResponseEntity<?> getTeams(@RequestBody TeamDto team){
        return ResponseEntity.ok(apiService.updateTeam(team));
    }
    @DeleteMapping("/team/delete-team/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        return ResponseEntity.ok(apiService.deleteTeam(id));
    }

    @GetMapping("/task")
    public ResponseEntity<?> getTasks(){
        return ResponseEntity.ok(apiService.getAllTasks());
    }
    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(apiService.getById(id));
    }
    @PostMapping("/task/create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskDto task){
        return ResponseEntity.ok(apiService.saveTask(task));
    }
    @PutMapping("/task/alter-task")
    public ResponseEntity<?> alterTask(@RequestBody TaskDto task){
        return ResponseEntity.ok(apiService.updateTask(task));
    }
    @DeleteMapping("/task/delete-task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        return ResponseEntity.ok(apiService.deleteTask(id));
    }

    @GetMapping("/feature/mudar-feature-de-time/{idtime}/{idfeature}")
    public ResponseEntity<?> alterarTimeDeUmaFeature(@PathVariable("idtime") Long idTime, @PathVariable("idfeature")Long idfeature){
        return ResponseEntity.ok(apiService.alterarTimeDeFeature(idTime, idfeature));
    }

    @GetMapping("/feature/tasks-from-feature/{id}")
    public ResponseEntity<?> getTasksFromFeature(@PathVariable Long id){
        return ResponseEntity.ok(apiService.getTasksFromFeature(id));

    }
}
