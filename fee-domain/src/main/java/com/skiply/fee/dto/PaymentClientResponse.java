package com.skiply.fee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentClientResponse {
  private Integer referenceNumber;
  private String status;
}
