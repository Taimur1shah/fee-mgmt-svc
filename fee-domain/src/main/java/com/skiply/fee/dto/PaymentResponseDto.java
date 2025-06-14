package com.skiply.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
  private Integer studentId;
  private String feeName;
  private String grade;
  private String receiptId;
  private Integer quantity;
  private Float amount;
  private TransactionDTO transactionDTO;

}
