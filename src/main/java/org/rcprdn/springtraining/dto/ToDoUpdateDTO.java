package org.rcprdn.springtraining.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoUpdateDTO {

  @NotNull
  @Positive
  private Long id;

  private Integer prio;

  private String description;

  @NotNull
  private Boolean done;

  private String title;

  private String dueDate;

}
