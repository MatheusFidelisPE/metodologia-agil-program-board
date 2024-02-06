package com.program.board.demo.model.dtos;

import com.program.board.demo.model.Feature;
import com.program.board.demo.model.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDto {

    private Long idFeature;
    private String title;
    private String hypothesis;
    private String acceptanceCriteria;
    private Integer priority;
    private LocalDate valueDate;
    private Integer effort;
    private List<Feature> dependencias;
    private List<Task> task;

}
