package com.skiply.fee.repository;

import com.skiply.fee.domain.Fee;
import java.util.Optional;

public interface FeeRepository {
  Fee save(Fee fee);
  Optional<Fee> findById(Integer id);
  Fee findByGradeAndFeeName(String grade,String feeName);
  void deleteById(Integer id);
}
