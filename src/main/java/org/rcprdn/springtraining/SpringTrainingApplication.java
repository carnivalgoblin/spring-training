package org.rcprdn.springtraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringTrainingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTrainingApplication.class, args);
  }
}
