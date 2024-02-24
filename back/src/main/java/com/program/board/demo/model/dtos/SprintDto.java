package com.program.board.demo.model.dtos;

import com.program.board.demo.model.Feature;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SprintDto {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}
