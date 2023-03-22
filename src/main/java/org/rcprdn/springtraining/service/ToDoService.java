package org.rcprdn.springtraining.service;

import lombok.RequiredArgsConstructor;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDoService {

  private ToDoRepository toDoRepository;

  public ToDo createToDo (ToDo toDo) {
    return toDoRepository.save(toDo);
  };

  public ToDo updateToDo (long id, ToDo toDo) {
    ToDo exisitingToDo = toDoRepository.findById(id).orElse(null);

    assert exisitingToDo != null;
    exisitingToDo.setTitle(toDo.getTitle());
    exisitingToDo.setDescription(toDo.getDescription());
    exisitingToDo.setDueDate(toDo.getDueDate());
    exisitingToDo.setPriority(toDo.getPriority());
    exisitingToDo.setDone(toDo.getDone());

    return toDoRepository.save(exisitingToDo);
  };

  public void deleteToDo(long id) {
    toDoRepository.deleteById(id);;
  };

  public List<ToDo> getAllToDos() {
    return toDoRepository.findAll();
  };

 public List<ToDo> getAllInProgress() {
   return toDoRepository.findByDone(false);
 }

 public List<ToDo> getAllDone() {
   return toDoRepository.findByDone(true);
 };

 public ToDo getToDo(Long id) {
   return toDoRepository.findById(id).orElse(null);
 }

 public long getCountCompletedToDos() {
   return toDoRepository.countByDone(true);
 };

 public long getCountOpenToDos() {
   return toDoRepository.countByDone(false);
 };

}
