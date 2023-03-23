package org.rcprdn.springtraining.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ToDoUpdate {

  @NotNull
  @Positive
  private Long Id;

  @NotNull
  private String description;

  @NotNull
  private Boolean done;

  public ToDoUpdate() {
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
