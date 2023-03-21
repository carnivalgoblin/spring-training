package org.rcprdn.springtraining.repository;

import org.rcprdn.springtraining.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
