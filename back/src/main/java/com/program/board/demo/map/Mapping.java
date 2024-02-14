package com.program.board.demo.map;

import com.program.board.demo.model.Feature;
import com.program.board.demo.model.Team;
import com.program.board.demo.model.dtos.FeatureDto;
import com.program.board.demo.model.dtos.TeamDto;
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
}
