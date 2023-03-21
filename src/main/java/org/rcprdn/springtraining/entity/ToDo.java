package org.rcprdn.springtraining.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // ATTRIBUTES

  public Boolean done;
  public Integer priority;
  public String title;
  public String description;
  public String dueDate;

}
