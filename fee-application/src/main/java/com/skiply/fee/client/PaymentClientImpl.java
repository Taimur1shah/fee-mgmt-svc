package com.skiply.fee.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skiply.fee.dto.PaymentClientResponse;
import com.skiply.fee.dto.PaymentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentClientImpl implements PaymentClient {

  @Value("${payment.service.url:http://127.0.0.1:8082/payment-service/api/payment}")
  private String paymentServiceUrl;

  private final RestTemplate restTemplate;

  public PaymentClientImpl(
      RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public PaymentClientResponse performTransaction(PaymentDto paymentDto) {
    try {
      System.out.println(new ObjectMapper().writeValueAsString(paymentDto));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto, headers);
    ResponseEntity<PaymentClientResponse> response = null;
    try{
      response = restTemplate.postForEntity(
          paymentServiceUrl, request, PaymentClientResponse.class
      );
    }catch (Exception ex){
      ex.printStackTrace();
    }


    PaymentClientResponse paymentClientResponse = new PaymentClientResponse();
    BeanUtils.copyProperties(response.getBody(), paymentClientResponse);
    return paymentClientResponse;
  }


}
