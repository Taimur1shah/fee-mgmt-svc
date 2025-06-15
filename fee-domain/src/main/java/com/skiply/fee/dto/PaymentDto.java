
package com.skiply.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

  private Integer studentId;
  private String feeName;
  private String grade;
  private Integer quantity;
  private Float amount;
  private String cardType;
  private String cardNumber;
 // private TransactionDTO transactionDTO;

}

