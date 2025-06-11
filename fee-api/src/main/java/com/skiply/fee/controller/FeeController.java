package com.skiply.fee.controller;

import com.skiply.fee.api.FeeApi;
import com.skiply.fee.api.model.FeeRequest;
import com.skiply.fee.api.model.FeeResponse;
import com.skiply.fee.domain.Fee;
import com.skiply.fee.service.FeeService;
import com.skiply.fee.service.impl.FeeServiceImpl;
import com.skiply.fee.util.FeeUtil;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FeeController implements FeeApi {

  private final FeeService feeService;

  public FeeController(FeeServiceImpl feeService) {
    this.feeService = feeService;
  }

  @SneakyThrows
  @Override
  public ResponseEntity<Object> addNewFee(FeeRequest feeReq){
    Fee fee = new Fee();
    BeanUtils.copyProperties(feeReq,fee);
    feeService.saveFee(fee);

    FeeResponse feeResp = new FeeResponse();
    BeanUtils.copyProperties(fee,feeResp);

    return ResponseEntity.ok(feeResp);
  }


  @Override
  public ResponseEntity<Object> getFeeById(Integer feeId){
    Optional<Fee> fee = feeService.getFeeById(feeId);
    if(fee.isPresent()){
      FeeResponse studentResp = new FeeResponse();
      BeanUtils.copyProperties(fee.get(),studentResp);
      return ResponseEntity.ok(studentResp);
    }else {
      FeeUtil studentUtil = new FeeUtil();
      return ResponseEntity.ok(studentUtil.getNotFoundErrorResponse());
    }
  }

  @Override
  public ResponseEntity<Object> getFeeByGrade(String grade){
    Fee fee = feeService.getFeeByGrade(grade);
    if(null !=fee){
      FeeResponse feeResp = new FeeResponse();
      BeanUtils.copyProperties(fee,feeResp);
      return ResponseEntity.ok(feeResp);
    }else {
      FeeUtil studentUtil = new FeeUtil();
      return ResponseEntity.ok(studentUtil.getNotFoundErrorResponse());
    }
  }
  @Override
  public ResponseEntity<Object> updateFeeById(Integer feeId,FeeRequest feeReq){
    Optional<Fee> existingFee = feeService.getFeeById(feeId);
    if(existingFee.isPresent()){
      Fee updateFee = new Fee();
      updateFee.setFeeId(feeId);
      BeanUtils.copyProperties(feeReq,updateFee);
      feeService.saveFee(updateFee);

      FeeResponse studentResp = new FeeResponse();
      BeanUtils.copyProperties(updateFee,studentResp);
      return ResponseEntity.ok(studentResp);
    }else {
      FeeUtil studentUtil = new FeeUtil();
      return ResponseEntity.ok(studentUtil.getNotFoundErrorResponse());
    }
  }

  @Override
  public ResponseEntity<Void> deleteFeeById(Integer feeId){
    Optional<Fee> student = feeService.getFeeById(feeId);
    if(student.isPresent()){
      feeService.deleteFee(feeId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      FeeUtil feeUtil = new FeeUtil();
      return ResponseEntity.notFound().build();
    }
  }
}
