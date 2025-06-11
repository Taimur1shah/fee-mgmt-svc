package com.skiply.fee.repository;

import com.skiply.fee.domain.Fee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeSpringDataJpa extends JpaRepository<Fee, Integer> {
  Fee findByGrade(String grade);
}
