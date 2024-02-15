package com.program.board.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFeature;
    private String title;
    private String hypothesis;
    private String acceptanceCriteria;
    private Integer priority;
    private LocalDate valueDate;
    private Integer effort;
    @ManyToMany
    @JoinTable(
            name = "dependecias",
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id")
    )
    private List<Feature> dependencias;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature", cascade = CascadeType.ALL)
    private List<Task> task;
    @ManyToOne()
    private Sprint sprint;
    @ManyToOne()
    private Team time;

    public void setDependencias(Feature dpd){
        this.getDependencias().add(dpd);
    }

    public void removeDependencia(Feature ind){
        this.dependencias.remove(ind);
    }


}
