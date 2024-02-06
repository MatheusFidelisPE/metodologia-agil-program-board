package com.program.board.demo.model.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TeamDto {

    private Long id;
    private String nomeTime;

}
