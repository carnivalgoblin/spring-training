package org.rcprdn.springtraining.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Service
public class ToDoService {

  private final ToDoRepository toDoRepository;
  Locale locale = new Locale("fr");
  ResourceBundle errorBundle = ResourceBundle.getBundle("ErrorResource", locale);

  public ToDo createToDo (ToDo toDo) {
    return toDoRepository.save(toDo);
  }

  public ToDo updateToDo (ToDo toDo) throws Exception {
    ToDo exisitingToDo = toDoRepository.findById(toDo.getId()).orElseThrow(() -> new Exception(errorBundle.getString("internalServerError")));

    assert exisitingToDo != null;
    exisitingToDo.setTitle(toDo.getTitle());
    exisitingToDo.setDescription(toDo.getDescription());
    exisitingToDo.setDueDate(toDo.getDueDate());
    exisitingToDo.setPriority(toDo.getPriority());
    exisitingToDo.setDone(toDo.getDone());

    return toDoRepository.save(exisitingToDo);
  }

  public void deleteToDo(long id) {
    toDoRepository.deleteById(id);
  }

  public List<ToDo> getAllToDos() {
    return toDoRepository.findAll();
  }

 public List<ToDo> getAllInProgress() {
   return toDoRepository.findByDone(false);
 }

 public List<ToDo> getAllDone() {
   return toDoRepository.findByDone(true);
 }

 public ToDo getToDo(Long id) {
   return toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorBundle.getString("entityNotFoundError")));
 }

 public long getCountCompletedToDos() {
   return toDoRepository.countByDone(true);
 }

 public long getCountOpenToDos() {
   return toDoRepository.countByDone(false);
 }

}
