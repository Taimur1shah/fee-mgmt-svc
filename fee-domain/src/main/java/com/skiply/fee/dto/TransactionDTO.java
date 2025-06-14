package com.skiply.fee.dto;


import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionDTO {

  private String transactionId;
  private Integer referenceNumber;
  private String status;
  private OffsetDateTime timestamp;

}
