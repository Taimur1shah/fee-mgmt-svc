package com.skiply.fee.service;

import com.skiply.fee.domain.Fee;
import com.skiply.fee.dto.PaymentDto;
import com.skiply.fee.dto.PaymentResponseDto;
import java.util.Optional;


public interface FeeService {

  Fee saveFee(Fee fee);

  Optional<Fee> getFeeById(Integer feeId);

  Fee getFeeByGradeAndFeeName(String grade,String feeName);

  void deleteFee(Integer feeId);

  PaymentResponseDto payFee(PaymentDto paymentDto);

}
