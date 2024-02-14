package com.program.board.demo.map;

import com.program.board.demo.model.Feature;
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
        
        return dto;

    }
    public List<FeatureDto> featureDtos(List<Feature> etts){
        List<FeatureDto> dtos = new ArrayList<>();
        
        for(Feature ett: etts){
            dtos.add(this.featureDto(ett));
        }
        return dtos;
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
}
