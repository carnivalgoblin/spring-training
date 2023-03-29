package org.rcprdn.springtraining.entity;

import lombok.Getter;

@Getter
public enum Permission {

  TODO_READ("todo:read"),

  TODO_READ_ALL("todo:read_all"),

  TODO_UPDATE("todo:update"),

  TODO_DELETE("todo:delete"),

  TODO_CREATE("todo:create");

  private final String permission;

  Permission(String permission) {
    this.permission = permission;

  }
}
