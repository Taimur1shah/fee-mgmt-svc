package com.skiply.fee.controller;

import com.skiply.fee.api.FeeApi;
import com.skiply.fee.api.model.AddNewFee201Response;
import com.skiply.fee.api.model.FeeRequest;
import com.skiply.fee.api.model.FeeResponse;
import com.skiply.fee.api.model.PayFeeRequest;
import com.skiply.fee.api.model.PaymentReceiptResponse;
import com.skiply.fee.domain.Fee;
import com.skiply.fee.dto.FeeDto;
import com.skiply.fee.dto.PaymentDto;
import com.skiply.fee.dto.PaymentResponseDto;
import com.skiply.fee.dto.TransactionDTO;
import com.skiply.fee.response.FeeResponseBuilder;
import com.skiply.fee.service.FeeService;
import com.skiply.fee.service.impl.FeeServiceImpl;
import com.skiply.fee.util.FeeUtil;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class FeeController implements FeeApi {

  private final FeeService feeService;

  public FeeController(FeeServiceImpl feeService) {
    this.feeService = feeService;
  }

  @SneakyThrows
  @Override
  public ResponseEntity<AddNewFee201Response> addNewFee(@RequestBody FeeRequest feeReq){
    Fee fee = new Fee();
    BeanUtils.copyProperties(feeReq,fee);
    Fee savedFee = feeService.saveFee(fee);

    return new ResponseEntity<>(
        FeeResponseBuilder.buildFeeCreateResponse(savedFee.getFeeId()),
        HttpStatus.CREATED);
  }


  @Override
  public ResponseEntity<Object> getFeeById(Integer feeId) {
    Fee fee = feeService.getFeeById(feeId)
        .orElseThrow(() -> new NoSuchElementException("Fee with ID " + feeId + " not found"));

    FeeResponse feeResp = new FeeResponse();
    BeanUtils.copyProperties(fee, feeResp);
    return ResponseEntity.ok(feeResp);
  }

  @Override
  public ResponseEntity<Object> updateFeeById(Integer feeId, FeeRequest feeReq) {
    feeService.getFeeById(feeId)
        .orElseThrow(() -> new NoSuchElementException("Fee with ID " + feeId + " not found"));

    Fee updateFee = new Fee();
    updateFee.setFeeId(feeId);
    BeanUtils.copyProperties(feeReq, updateFee);
    feeService.saveFee(updateFee);

    FeeResponse feeResp = new FeeResponse();
    BeanUtils.copyProperties(updateFee, feeResp);
    return ResponseEntity.ok(feeResp);
  }

  @Override
  public ResponseEntity<Void> deleteFeeById(Integer feeId) {
    feeService.getFeeById(feeId)
        .orElseThrow(() ->new NoSuchElementException("Fee with ID " + feeId + " not found"));

    feeService.deleteFee(feeId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<PaymentReceiptResponse> payFee(PayFeeRequest payFeeRequest) {
    PaymentDto paymentDto = new PaymentDto();
    BeanUtils.copyProperties(payFeeRequest, paymentDto);

    PaymentResponseDto paymentResponseDto = feeService.payFee(paymentDto);

    return new ResponseEntity<>(
        FeeResponseBuilder.buildPaymentReceiptResponse(paymentResponseDto),
        HttpStatus.OK);
  }
}

