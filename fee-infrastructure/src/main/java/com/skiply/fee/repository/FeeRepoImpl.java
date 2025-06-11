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
  public void save(Fee student) {
    jpaRepository.save(student);
  }

  @Override
  public Optional<Fee> findById(Integer id) {
    return jpaRepository.findById(id);
  }

  @Override
  public Fee findByGrade(String grade) {
    return jpaRepository.findByGrade(grade);
  }

  @Override
  public void deleteById(Integer id) {
    jpaRepository.deleteById(id);
  }


}