package com.program.board.demo.repository;

import org.springframework.stereotype.Repository;
import com.program.board.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
