package com.program.board.demo.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DependenciaDto {


    private Long idIndependente;
    private Long idDependente;

}
