package org.rcprdn.springtraining.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ToDoUpdateDTO {

  @NotNull
  @Positive
  private Long id;

  @NotNull
  private String description;

  @NotNull
  private Boolean done;

  public ToDoUpdateDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }
}
