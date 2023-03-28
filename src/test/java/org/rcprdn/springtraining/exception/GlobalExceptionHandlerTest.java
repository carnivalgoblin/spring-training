package org.rcprdn.springtraining.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.rcprdn.springtraining.controller.ToDoController;
import org.rcprdn.springtraining.entity.ToDo;
import org.rcprdn.springtraining.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GlobalExceptionHandlerTest.class)
class GlobalExceptionHandlerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MessageSource messageSource;

  @MockBean
  private ToDoService toDoService;

  @MockBean
  private ModelMapper modelMapper;

  @BeforeEach
  public void setup() throws Exception {

    when(messageSource.getMessage(any(), any(), eq(Locale.ENGLISH))).thenReturn("english error");
    when(messageSource.getMessage(any(), any(), eq(Locale.GERMAN))).thenReturn("german error");

    this.mockMvc = MockMvcBuilders.standaloneSetup(new ToDoController(toDoService, modelMapper))
            .setControllerAdvice(new GlobalExceptionHandler(messageSource)).build();

  }

 // @Test
 // public void entityNotFoundTest() throws Exception {
//
 //   when(mockMvc.perform(get("/api/todos/6").locale(Locale.GERMAN)));
//
 //   mockMvc.perform(get("/api/todos/6").locale(Locale.GERMAN))
 //           .andExpect(status().isNotFound())
 //           .andExpect(content().string("404"));
 // }

}