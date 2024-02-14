package com.program.board.demo.service;

import com.program.board.demo.map.Mapping;
import com.program.board.demo.model.Epic;


import com.program.board.demo.model.Sprint;
import com.program.board.demo.model.Team;
import com.program.board.demo.model.dtos.TeamDto;
import com.program.board.demo.model.Task;
import com.program.board.demo.model.dtos.TaskDto;




import com.program.board.demo.repository.FeatureRepository;
import com.program.board.demo.repository.EpicRepository;
import com.program.board.demo.model.Feature;
import com.program.board.demo.model.dtos.FeatureDto;

import com.program.board.demo.repository.SprintRepository;
import com.program.board.demo.repository.TeamRepository;

import com.program.board.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
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


    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private TeamRepository teamRepository;


    @Autowired
    private TaskRepository taskRepository;



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
        Team tm = teamRepository.findById(featureDto.getIdTime()).get();
        Sprint spt = sprintRepository.findById(featureDto.getIdSprint()).get();
        featureModel.setSprint(spt);
        featureModel.setTime(tm);

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


    public FeatureDto alterarTimeDeFeature(Long idTime, Long idfeature) {
        Feature ft = featureRepository.findById(idfeature).get();
        Team tm = teamRepository.findById(idTime).get();

        ft.setTime(tm);
        featureRepository.save(ft);
        return map.featureDto(ft);
    }


    public List<TaskDto> getTasksFromFeature(Long id) {
        List<Task> tasks = taskRepository.findTasksFromFeature(id);
        return map.taskDtos(tasks);
    }

    public TeamDto deleteTeam(Long id){
        Team ett = teamRepository.findById(id).get();
        teamRepository.deleteById(id);

        return map.teamDto(ett);
    }

    public TeamDto saveTeam(TeamDto dto){
        Team ett = new Team();
        BeanUtils.copyProperties(dto,ett);
        teamRepository.save(ett);
        return dto;
    }
    public List<TeamDto> getAllTeams(){
        List<Team> etts = teamRepository.findAll();

        return map.teamToDtos(etts);
    }
    public TeamDto getTeamById(Long id){
        Team ett = teamRepository.findById(id).get();
        return map.teamDto(ett);
    }


    public TaskDto getById(Long id){
        Task ett = taskRepository.findById(id).get();
        return map.taskDto(ett);
    }
    public List<TaskDto> getAllTasks(){
        List<Task> etts = taskRepository.findAll();
        return map.taskDtos(etts);
    }
    public TaskDto updateTask(TaskDto dto){
        Feature ft = featureRepository.findById(dto.getFeatureId())
                .orElseThrow();
        Task ett = taskRepository.findById(dto.getId()).get();

        BeanUtils.copyProperties(dto, ett);
        ett.setFeature(ft);
        taskRepository.save(ett);
        return dto;
    }
    public TaskDto saveTask(TaskDto dto){
        Feature ft = featureRepository.findById(dto.getFeatureId())
                .orElseThrow();
        Task tk = new Task();
        BeanUtils.copyProperties(dto, tk);
        tk.setFeature(ft);
        taskRepository.save(tk);
        return dto;
    }
    public TaskDto deleteTask(Long id) {
        Task tk = taskRepository.findById(id).get();
        taskRepository.deleteById(id);
        return map.taskDto(tk);
    }


    public TeamDto updateTeam(TeamDto team) {

        Team ett = teamRepository.findById(team.getId()).get();
        BeanUtils.copyProperties(team,ett);
        teamRepository.save(ett);
        return team;


    }
}
