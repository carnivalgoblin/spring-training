package org.rcprdn.springtraining.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rcprdn.springtraining.dto.ToDoCreateDTO;
import org.rcprdn.springtraining.dto.ToDoUpdateDTO;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {

  private final ToDoService toDoService;
  private final ModelMapper modelMapper;

  // ADD/DELETE TO_DO
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//  @LogExecutionTime
  public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO) {
    return new ResponseEntity<>(this.toDoService.createToDo(modelMapper.map(toDoCreateDTO, ToDo.class)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
//  @LogExecutionTime
  public void delete(@Valid @PathVariable("id") Long id) {
    toDoService.deleteToDo(id);
  }

  // GET ALL DEFAULT
  @GetMapping
//  @LogExecutionTime
  public List<ToDo> getAllTodos() {
    List<ToDo> allTodos = toDoService.getAllToDos();
    return allTodos;
  }

  // GET COMPLETED AND OPEN TODOS
  @GetMapping("/completed")
//  @LogExecutionTime
  public List<ToDo> getCompletedTodos() {
    List<ToDo> toDos = toDoService.getAllDone();
    return toDos;
  }

  @GetMapping("/open")
//  @LogExecutionTime
  public List<ToDo> allInProgress () {
    List<ToDo> allInProgress = toDoService.getAllInProgress();
    return allInProgress;
  }

  // GET SINGLE TO_DO
  @GetMapping("/{id}")
//  @LogExecutionTime
  public ToDo getToDo(@PathVariable("id") Long id) throws EntityNotFoundException {
    return toDoService.getToDo(id);
  }

  // COUNTS
  @GetMapping("/completed/count")
//  @LogExecutionTime
  public Long completedCount() {
    return toDoService.getCountCompletedToDos();
  }

  @GetMapping("/open/count")
//  @LogExecutionTime
  public Long openCount() {
    return toDoService.getCountOpenToDos();
  }

  // UPDATE
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//  @LogExecutionTime
  public ToDo updateToDo(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO) throws Exception {
    ToDo toDo = this.toDoService.getToDo(toDoUpdateDTO.getId());

    modelMapper.map(toDoUpdateDTO, toDo);

    return this.toDoService.updateToDo(toDo);

  }


}
