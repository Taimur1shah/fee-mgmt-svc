package com.skiply.fee.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeDto {

  private Integer feeId;


  private String feeName;

  private String grade;

  private Double feeCharges;

}
