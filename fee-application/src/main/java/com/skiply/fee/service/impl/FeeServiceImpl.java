package com.skiply.fee.service.impl;

import com.skiply.fee.domain.Fee;
import com.skiply.fee.repository.FeeRepository;
import com.skiply.fee.service.FeeService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {

  private final FeeRepository repository;

  public FeeServiceImpl(FeeRepository repository) {
    this.repository = repository;
  }


  @Override
  public void saveFee(Fee fee) {
     repository.save(fee);
  }

  @Override
  public Optional<Fee>  getFeeById(Integer feeId) {
    Optional<Fee> optionalFee = repository.findById(feeId);
    return optionalFee;
  }

  @Override
  public Fee getFeeByGrade(String grade) {
    return repository.findByGrade(grade);
  }

  @Override
  public void deleteFee(Integer feeId) {
    repository.deleteById(feeId);
  }
}
