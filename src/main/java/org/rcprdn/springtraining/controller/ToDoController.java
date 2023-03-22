package org.rcprdn.springtraining.controller;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.service.ToDoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {

  private final ToDoService toDoService;

  // ADD/DELETE TO_DO
  @PostMapping
  public ToDo createToDo(@Valid @RequestBody ToDo toDo) {
    return toDoService.createToDo(toDo);
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
  @PutMapping("/{id}")
  public ToDo updateToDo(@Valid @PathVariable("id") Long id, @Valid @RequestBody ToDo toDo) {
    return toDoService.updateToDo(id, toDo);
  }

}
