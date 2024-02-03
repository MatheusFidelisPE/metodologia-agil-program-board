package com.programboard.programboard.repository;

import com.programboard.programboard.model.EpicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EpicRepository extends JpaRepository<EpicModel, Long> {

    Optional<EpicModel> findAllById();
    Optional<EpicModel> findEpicModelById(Long id);
}
