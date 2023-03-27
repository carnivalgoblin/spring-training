package org.rcprdn.springtraining.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rcprdn.springtraining.entity.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ToDoRepositoryTest {

  @Autowired
  private ToDoRepository toDoRepository;

  private ToDo toDoOne;
  private ToDo toDoTwo;
  private ToDo toDoThree;

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

    toDoRepository.save(toDoOne);
    toDoRepository.save(toDoTwo);
    toDoRepository.save(toDoThree);
  }
  // Tests

  @Test
  void findByStatus() {
    assertTrue(toDoRepository.findByDone(true).contains(toDoThree));
    assertFalse(toDoRepository.findByDone(true).contains(toDoOne));
    assertFalse(toDoRepository.findByDone(true).contains(toDoTwo));
  }

  @Test
  void countByStatus() {
    assertEquals( 1, toDoRepository.countByDone(true));
    assertEquals( 2, toDoRepository.countByDone(false));
  }

}