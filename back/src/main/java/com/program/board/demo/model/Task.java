package com.program.board.demo.model;


import com.program.board.demo.model.qualificadores.Level;
import com.program.board.demo.model.qualificadores.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @NotNull
    private Feature feature;

    private String titulo;
    private String descricao;
    private String dev;
    private LocalDate deadLine;
    private Level prioridade;
    private Status status;
//    data conclusão da Sprint
    private LocalDate endDate;
    private String notas;





}
