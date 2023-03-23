package org.rcprdn.springtraining.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class ToDo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // ATTRIBUTES

  private Boolean done;
  private Integer priority;
  private String title;
  private String description;
  private String dueDate;

}
