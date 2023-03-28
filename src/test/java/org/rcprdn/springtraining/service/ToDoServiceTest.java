package org.rcprdn.springtraining.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.repository.ToDoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

  private ToDo toDoOne;
  private ToDo toDoTwo;
  private ToDo toDoThree;

  List<ToDo> todoList = new ArrayList<>();

  @Mock
  private ToDoRepository toDoRepository;

  @InjectMocks
  private ToDoService toDoService;

  @BeforeEach
  public void setup() {

    toDoOne = new ToDo();
    toDoOne.setTitle("Erstes ToDo");
    toDoOne.setDone(false);
    toDoOne.setDescription("Dies ist das erste ToDo.");
    toDoOne.setPriority(1);
    toDoOne.setDueDate("Morgen");

    toDoTwo = new ToDo();
    toDoTwo.setTitle("Zweites ToDo");
    toDoTwo.setDone(false);
    toDoTwo.setDescription("Dies ist das zweite ToDo.");
    toDoTwo.setPriority(2);
    toDoTwo.setDueDate("Heute");

    toDoThree = new ToDo();
    toDoThree.setTitle("Drittes ToDo");
    toDoThree.setDone(true);
    toDoThree.setDescription("Dies ist das dritte ToDo.");
    toDoThree.setPriority(2);
    toDoThree.setDueDate("Heute");

  }

  @Test
  void getTodo() {
    when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(toDoOne));
    assertEquals(toDoOne, toDoService.getToDo(any(Long.class)));
  }

  @Test
  void exception() {
    // when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> toDoService.getToDo(0L));
  }
}