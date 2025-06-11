package com.skiply.fee;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.skiply.fee", "com.skiply.fee.api"})
public class FeeApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeeApplication.class, args);
  }


}
