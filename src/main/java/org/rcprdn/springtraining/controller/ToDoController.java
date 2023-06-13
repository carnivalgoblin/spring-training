package org.rcprdn.springtraining.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rcprdn.springtraining.dto.ToDoCreateDTO;
import org.rcprdn.springtraining.dto.ToDoUpdateDTO;
import org.rcprdn.springtraining.entity.Role;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.helpers.PermissionAll;
import org.rcprdn.springtraining.helpers.PermissionNonUser;
import org.rcprdn.springtraining.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @PreAuthorize("hasRole('TODO_CREATE')")
//  @LogExecutionTime
  public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO) {
    return new ResponseEntity<>(this.toDoService.createToDo(modelMapper.map(toDoCreateDTO, ToDo.class)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('TODO_DELETE')")
//  @LogExecutionTime
  public void delete(@Valid @PathVariable("id") Long id) {
    toDoService.deleteToDo(id);
  }

  // GET ALL DEFAULT
  @GetMapping
  @PreAuthorize("hasAnyRole('TODO_READ')")
//  @LogExecutionTime
  public List<ToDo> getAllTodos() {
    List<ToDo> allTodos = toDoService.getAllToDos();
    return allTodos;
  }

  // GET COMPLETED AND OPEN TODOS
  @GetMapping("/completed")
  @PreAuthorize("hasAnyRole('TODO_READ_ALL')")
//  @LogExecutionTime
  public List<ToDo> getCompletedTodos() {
    List<ToDo> toDos = toDoService.getAllDone();
    return toDos;
  }

  @GetMapping("/open")
  @PreAuthorize("hasAnyRole('TODO_READ_ALL')")
//  @LogExecutionTime
  public List<ToDo> allInProgress () {
    List<ToDo> allInProgress = toDoService.getAllInProgress();
    return allInProgress;
  }

  // GET SINGLE TO_DO
  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('TODO_READ')")
//  @LogExecutionTime
  public ToDo getToDo(@PathVariable("id") Long id) throws EntityNotFoundException {
    return toDoService.getToDo(id);
  }

  // COUNTS
  @GetMapping("/completed/count")
  @PreAuthorize("hasAnyRole('TODO_READ_ALL')")
//  @LogExecutionTime
  public Long completedCount() {
    return toDoService.getCountCompletedToDos();
  }

  @GetMapping("/open/count")
  @PreAuthorize("hasAnyRole('TODO_READ_ALL')")
//  @LogExecutionTime
  public Long openCount() {
    return toDoService.getCountOpenToDos();
  }

  // UPDATE
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAnyRole('TODO_UPDATE')")
//  @LogExecutionTime
  public ToDo updateToDo(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO) throws Exception {
    ToDo toDo = this.toDoService.getToDo(toDoUpdateDTO.getId());

    modelMapper.map(toDoUpdateDTO, toDo);

    return this.toDoService.updateToDo(toDo);

  }


}
