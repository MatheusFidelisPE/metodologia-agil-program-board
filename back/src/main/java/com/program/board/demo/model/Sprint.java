package com.program.board.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Feature> features;

}
