package org.example.authentication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.Roles;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
@Getter
@Setter
@ToString
public class UserSession implements Serializable {
  private static final long serialVersionUID = 1L;
  private Roles role;
  private String userId;
}