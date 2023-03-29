package org.rcprdn.springtraining.controller;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoController.class)
class ToDoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ToDoService toDoService;

  @MockBean
  private ModelMapper modelMapper;

  private ToDo toDoOne;
  private ToDo toDoTwo;
  private ToDo toDoThree;

  List<ToDo> todoList = new ArrayList<>();

  Gson gson = new Gson();

  @BeforeEach
  public void setup() throws Exception {

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

    todoList.add(toDoOne);
    todoList.add(toDoTwo);
    todoList.add(toDoThree);
  }

  @Test
  public void test() throws Exception {

    // mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1))).andExpect((ResultMatcher) jsonPath("$[0].title", is(toDoOne.getTitle())));

  }

  @Test
  void createToDo() {
  }

  @Test
  @WithMockUser
  void delete() throws Exception {
    mockMvc.perform(get("/api/todos/1"))
            .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  void getAllTodos() throws Exception {

    // String jsonTodos = gson.toJson(todoList);
    // when(this.toDoService.getAllToDos()).thenReturn(jsonTodos);

    mockMvc.perform(get("/api/todos"))
            .andExpect(status().isOk())
            .andExpect(content().json(
                    """
                            [
                              {
                                "id": 1,
                                "done": false,
                                "priority": 1,
                                "title": "Erstes ToDo",
                                "description": "Dies ist das erste ToDo.",
                                "dueDate": "Morgen"
                              },
                              {
                                "id": 2,
                                "done": false,
                                "priority": 2,
                                "title": "Zweites ToDo",
                                "description": "Dies ist das zweite ToDo.",
                                "dueDate": "Heute"
                              },
                              {
                                "id": 3,
                                "done": true,
                                "priority": 2,
                                "title": "Drittes ToDo",
                                "description": "Dies ist das dritte ToDo.",
                                "dueDate": "Heute"
                              }
                            ]
                            """
            ));
  }

  @Test
  @WithMockUser
  void getToDo() throws Exception {
    when(toDoService.getToDo(1L)).thenReturn(toDoOne);
    mockMvc.perform(get("/api/todos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value(toDoOne.getTitle()))
            .andExpect(jsonPath("$.done").value(toDoOne.getDone()));

    verify(toDoService, times(1)).getToDo(1L);
  }

  @Test
  void updateToDo() {
  }
}