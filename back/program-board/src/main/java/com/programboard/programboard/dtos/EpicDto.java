package com.programboard.programboard.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EpicDto {

    private List<String> titles;
    private List<String> descriptions;


}
