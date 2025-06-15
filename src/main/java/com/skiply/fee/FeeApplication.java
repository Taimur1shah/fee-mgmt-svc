package com.skiply.fee;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = {"com.skiply.fee", "com.skiply.fee.api"})
public class FeeApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeeApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(5000);
    factory.setConnectionRequestTimeout(5000);
    return new RestTemplate(factory);
  }
}
