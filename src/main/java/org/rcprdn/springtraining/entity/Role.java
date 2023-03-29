package org.rcprdn.springtraining.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Role {

  ADMIN(Set.of(
          Permission.TODO_READ,
          Permission.TODO_UPDATE,
          Permission.TODO_DELETE,
          Permission.TODO_CREATE,
          Permission.TODO_READ_ALL
  )),

  USER(Set.of(
          Permission.TODO_READ,
          Permission.TODO_UPDATE,
          Permission.TODO_CREATE
  )),

  ANALYST(Set.of(
          Permission.TODO_READ,
          Permission.TODO_READ_ALL
  ));

  private final Set<Permission> permissions;

  Role(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    return getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
  }

}
