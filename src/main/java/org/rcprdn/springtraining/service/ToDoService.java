package org.rcprdn.springtraining.service;

import lombok.RequiredArgsConstructor;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  public void deleteToDo (long id) {
    ToDo exisitingToDo = toDoRepository.findById(id).orElse(null);

    assert exisitingToDo != null;
    toDoRepository.delete(exisitingToDo);
  };

  public List<ToDo> getAllToDos () {
    List<ToDo> allToDos = new ArrayList<ToDo>();
    toDoRepository.findAll().add((ToDo) allToDos);

    return  allToDos;
  };

 public List<ToDo> getAllInProgress () {
   Stream<ToDo> allToDos = (Stream<ToDo>) toDoRepository.findAll();

   return allToDos.filter(d -> d.getDone() == false).collect(Collectors.toList());
 };

 public List<ToDo> getAllDone () {
   Stream<ToDo> allToDos = (Stream<ToDo>) toDoRepository.findAll();

   return allToDos.filter(d -> d.getDone() == true).collect(Collectors.toList());
 };

 public long getCountCompletedToDos () {
   List<ToDo> allToDos = toDoRepository.findAll().stream().toList();
   Long count = 0L;

   for (ToDo todo : allToDos) {
     if (todo.getDone()) {
       count++;
     }
   }
   return count;
 };

 public long getCountOpenToDos () {
   List<ToDo> allToDos = toDoRepository.findAll().stream().toList();
   Long count = 0L;

   for (ToDo todo : allToDos) {
     if (!todo.getDone()) {
       count++;
     }
   }
   return count;
 };

}
