package com.program.board.demo.model.dtos;

import com.program.board.demo.model.Feature;
import com.program.board.demo.model.qualificadores.Level;
import com.program.board.demo.model.qualificadores.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private Long featureId;
    private String titulo;
    private String descricao;
    private String dev;
    private LocalDate deadLine;
    private Level prioridade;
    private Status status;
    private LocalDate endDate;
    private String notas;

}
