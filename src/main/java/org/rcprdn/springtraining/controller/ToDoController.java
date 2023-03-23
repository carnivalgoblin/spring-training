package org.rcprdn.springtraining.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rcprdn.springtraining.dto.ToDoCreate;
import org.rcprdn.springtraining.dto.ToDoUpdate;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.service.ToDoService;
import org.springframework.http.MediaType;
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
  public ToDo createToDo(@Valid @RequestBody ToDoCreate toDoCreate) {
    return this.toDoService.createToDo(modelMapper.map(toDoCreate, ToDo.class));
  }

  @DeleteMapping("/{id}")
  public void delete(@Valid @PathVariable("id") Long id) {
    toDoService.deleteToDo(id);
  }

  // GET ALL DEFAULT
  @GetMapping
  public List<ToDo> getAllTodos() {
    List<ToDo> allTodos = toDoService.getAllToDos();
    return allTodos;
  }

  // GET COMPLETED AND OPEN TODOS
  @GetMapping("/completed")
  public List<ToDo> getCompletedTodos() {
    List<ToDo> toDos = toDoService.getAllDone();
    return toDos;
  }

  @GetMapping("/open")
  public List<ToDo> allInProgress () {
    List<ToDo> allInProgress = toDoService.getAllInProgress();
    return allInProgress;
  }

  // GET SINGLE TO_DO
  @GetMapping("/{id}")
  public ToDo getToDo(@PathVariable("id") Long id) {
    return toDoService.getToDo(id);
  }

  // COUNTS
  @GetMapping("/completed/count")
  public Long completedCount() {
    return toDoService.getCountCompletedToDos();
  }

  @GetMapping("/open/count")
  public Long openCount() {
    return toDoService.getCountOpenToDos();
  }

  // UPDATE
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ToDo updateToDo(@Valid @RequestBody ToDoUpdate toDoUpdate) {
    ToDo toDo = this.toDoService.getToDo(toDoUpdate.getId());

    modelMapper.map(toDoUpdate, toDo);

    return this.toDoService.updateToDo(toDo);

  }

}
