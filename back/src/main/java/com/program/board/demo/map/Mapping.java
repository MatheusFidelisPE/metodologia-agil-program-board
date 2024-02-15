package com.program.board.demo.map;

import com.program.board.demo.model.Feature;
import com.program.board.demo.model.Sprint;
import com.program.board.demo.model.Team;
import com.program.board.demo.model.dtos.SprintDto;
import com.program.board.demo.model.dtos.TeamDto;
import com.program.board.demo.model.Task;
import com.program.board.demo.model.dtos.FeatureDto;
import com.program.board.demo.model.dtos.TaskDto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {

    public FeatureDto featureDto(Feature ett){

        FeatureDto dto = new FeatureDto();
        dto.setIdFeature(ett.getIdFeature());
        dto.setTitle(ett.getTitle());
        dto.setEffort(ett.getEffort());
        dto.setPriority(ett.getPriority());
        dto.setHypothesis(ett.getHypothesis());
        dto.setAcceptanceCriteria(ett.getAcceptanceCriteria());
        dto.setValueDate(ett.getValueDate());
        dto.setIdTime(ett.getTime().getId());
        dto.setIdSprint(ett.getSprint().getId());

        return dto;

    }
    public List<FeatureDto> featureDtos(List<Feature> etts){
        List<FeatureDto> dtos = new ArrayList<>();
        
        for(Feature ett: etts){
            dtos.add(this.featureDto(ett));
        }
        return dtos;
    }


    public List<TeamDto> teamToDtos(List<Team> etts) {
        List<TeamDto> dtos = new ArrayList<>();

        for(Team ett: etts){
            dtos.add(this.teamDto(ett));
        }
        return dtos;
    }

    public TeamDto teamDto(Team ett) {
        TeamDto dto = new TeamDto();

        dto.setId(ett.getId());
        dto.setNome(ett.getNome());

        return dto;
    }
    public TaskDto taskDto(Task ett) {
        TaskDto dto = new TaskDto();

        dto.setTitulo(ett.getTitulo());
        dto.setId(ett.getId());
        dto.setDescricao(ett.getDescricao());
        dto.setDev(ett.getDev());
        dto.setDeadLine(ett.getDeadLine());
        dto.setPrioridade(ett.getPrioridade());
        dto.setStatus(ett.getStatus());
        dto.setEndDate(ett.getEndDate());
        dto.setNotas(ett.getNotas());
        dto.setFeatureId(ett.getFeature().getIdFeature());
        return dto;

    }
    public List<TaskDto> taskDtos(List<Task> etts){
        List<TaskDto> dtos = new ArrayList<>();

        for(Task ett: etts){
            dtos.add(this.taskDto(ett));
        }
        return dtos;

    }

    public List<SprintDto> sprintDtos(List<Sprint> sprints) {
        List<SprintDto> dtos = new ArrayList<>();

        for(Sprint spt: sprints){
            dtos.add(this.sprintDto(spt));
        }

        return dtos;
    }

    public SprintDto sprintDto(Sprint spt) {

        SprintDto dto = new SprintDto();

        dto.setDataFim(spt.getDataFim());
        dto.setDataInicio(spt.getDataInicio());
        dto.setId(spt.getId());

        return dto;
    }
}
