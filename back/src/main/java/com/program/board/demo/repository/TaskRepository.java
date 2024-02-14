package com.program.board.demo.repository;

import com.program.board.demo.model.Task;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
 
    @Query(value = "select * from task where feature_id_feature = ?1 ; ", nativeQuery = true)
    List<Task> findTasksFromFeature(Long idFeature);

}
