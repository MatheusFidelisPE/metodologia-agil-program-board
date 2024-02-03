package com.programboard.programboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EpicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Override
    public String toString(){
        return "Epico {" +
                "Id: " + this.id +
                ", Titulo: " + this.title +
                ", Descricao: " + this.description +
                "}";
    }
}
