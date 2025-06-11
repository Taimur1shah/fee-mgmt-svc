package com.skiply.fee.service;

import com.skiply.fee.domain.Fee;
import java.util.Optional;


public interface FeeService {

  void saveFee(Fee fee);

  Optional<Fee> getFeeById(Integer feeId);

  Fee getFeeByGrade(String grade);

  void deleteFee(Integer feeId);

}
