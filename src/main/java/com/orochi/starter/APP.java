package com.orochi.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.orochi.starter.**"})
@MapperScan(basePackages = {"com.orochi.starter.service.mapper"})
public class APP {

  public static void main(String[] args) {
    SpringApplication.run(APP.class, args);
  }
}
