package com.skiply.fee.repository;

import com.skiply.fee.domain.Fee;
import java.util.Optional;

public interface FeeRepository {
  void save(Fee fee);
  Optional<Fee> findById(Integer id);
  Fee findByGrade(String grade);
  void deleteById(Integer id);
}
