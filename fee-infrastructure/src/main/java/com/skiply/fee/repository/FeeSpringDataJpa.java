package com.skiply.fee.repository;

import com.skiply.fee.domain.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeSpringDataJpa extends JpaRepository<Fee, Integer> {
  Fee findByGradeAndFeeName(String grade,String feeName);
}
