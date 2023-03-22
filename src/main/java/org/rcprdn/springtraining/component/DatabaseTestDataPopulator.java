package org.rcprdn.springtraining.component;

import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTestDataPopulator implements CommandLineRunner {

  private ToDoRepository toDoRepository;

  public DatabaseTestDataPopulator(@Autowired ToDoRepository toDoRepository) {
    this.toDoRepository = toDoRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    ToDo toDoOne = new ToDo();
    toDoOne.setTitle("Erstes ToDo");
    toDoOne.setDone(false);
    toDoOne.setDescription("Dies ist das erste ToDo.");
    toDoOne.setPriority(1);
    toDoOne.setDueDate("Morgen");

    ToDo toDoTwo = new ToDo();
    toDoTwo.setTitle("Zweites ToDo");
    toDoTwo.setDone(false);
    toDoTwo.setDescription("Dies ist das zweite ToDo.");
    toDoTwo.setPriority(2);
    toDoTwo.setDueDate("Heute");

    ToDo toDoThree = new ToDo();
    toDoThree.setTitle("Drittes ToDo");
    toDoThree.setDone(true);
    toDoThree.setDescription("Dies ist das dritte ToDo.");
    toDoThree.setPriority(2);
    toDoThree.setDueDate("Heute");

    toDoRepository.save(toDoOne);
    toDoRepository.save(toDoTwo);
    toDoRepository.save(toDoThree);

  }

}
