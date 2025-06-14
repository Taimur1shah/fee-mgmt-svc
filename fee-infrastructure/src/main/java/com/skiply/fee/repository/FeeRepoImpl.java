package com.skiply.fee.repository;

import com.skiply.fee.domain.Fee;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class FeeRepoImpl implements FeeRepository {

  private final FeeSpringDataJpa jpaRepository;

  public FeeRepoImpl(FeeSpringDataJpa jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public Fee save(Fee fee) {
    return jpaRepository.save(fee);
  }

  @Override
  public Optional<Fee> findById(Integer id) {
    return jpaRepository.findById(id);
  }

  @Override
  public Fee findByGradeAndFeeName(String grade, String feeName) {
     return jpaRepository.findByGradeAndFeeName(grade,feeName);
  }

  @Override
  public void deleteById(Integer id) {
    jpaRepository.deleteById(id);
  }


}