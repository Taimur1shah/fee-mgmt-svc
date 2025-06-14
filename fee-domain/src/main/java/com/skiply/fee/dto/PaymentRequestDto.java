package com.skiply.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentRequestDto {

  private Integer studentId;
  private Integer quantity;
  private String grade;
  private String feeName;
  private Double amount;
  private String cardNumber;
  private String cardType;

}
