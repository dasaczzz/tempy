package com.dasaczzz.tempy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TempyApplication {

  public static void main(String[] args) {
    SpringApplication.run(TempyApplication.class, args);
  }

}
