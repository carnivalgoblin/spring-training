package org.rcprdn.springtraining.dto;

import jakarta.validation.constraints.NotBlank;

public class ToDoCreateDTO {

  private String description;
  @NotBlank
  private String title;
  private Boolean done;
  private Integer priority;
  private String dueDate;

  public ToDoCreateDTO() {
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

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }
}
