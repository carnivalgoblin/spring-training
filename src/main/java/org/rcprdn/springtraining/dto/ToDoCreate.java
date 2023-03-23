package org.rcprdn.springtraining.dto;

import jakarta.validation.constraints.NotNull;

public class ToDoCreate {

  @NotNull
  private String description;
  @NotNull
  private String title;

  public ToDoCreate() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
