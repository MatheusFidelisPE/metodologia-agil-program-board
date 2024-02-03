package com.programboard.programboard.repository;

import com.programboard.programboard.model.FeatureModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureModel, UUID> {
}
