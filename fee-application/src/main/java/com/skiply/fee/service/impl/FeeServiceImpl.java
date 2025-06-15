package com.skiply.fee.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.skiply.fee.client.PaymentClient;
import com.skiply.fee.domain.Fee;

import com.skiply.fee.dto.PaymentClientResponse;
import com.skiply.fee.dto.PaymentDto;
import com.skiply.fee.dto.PaymentRequestDto;
import com.skiply.fee.dto.PaymentResponseDto;
import com.skiply.fee.dto.TransactionDTO;
import com.skiply.fee.repository.FeeRepository;
import com.skiply.fee.service.FeeService;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImpl implements FeeService {

  private final FeeRepository repository;
  private final PaymentClient paymentClient;

  public FeeServiceImpl(FeeRepository repository, PaymentClient paymentClient) {
    this.repository = repository;
    this.paymentClient = paymentClient;
  }


  @Override
  public Fee saveFee(Fee fee) {
     return repository.save(fee);
  }

  @Cacheable("fees")
  @Override
  public Optional<Fee>  getFeeById(Integer feeId) {
    Optional<Fee> optionalFee = repository.findById(feeId);
    return optionalFee;
  }

  @Cacheable("feegrade")
  @Override
  public Fee getFeeByGradeAndFeeName(String grade, String feeName) {
    return repository.findByGradeAndFeeName(grade,feeName);
  }

  @Override
  public void deleteFee(Integer feeId) {
    repository.deleteById(feeId);
  }

  @Override
  public PaymentResponseDto payFee(PaymentDto paymentDto) {
    Fee fee = getFeeByGradeAndFeeName(paymentDto.getGrade(),paymentDto.getFeeName());
    paymentDto.setAmount((float) (paymentDto.getQuantity()* fee.getFeeCharges()));
    paymentDto.setStudentId(paymentDto.getStudentId());

    PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
    paymentRequestDto.setStudentId(paymentDto.getStudentId());
    paymentRequestDto.setGrade(paymentDto.getGrade());
    paymentRequestDto.setQuantity(paymentDto.getQuantity());
    paymentRequestDto.setCardNumber(paymentDto.getCardNumber());
    paymentRequestDto.setCardType(paymentDto.getCardType());
    paymentRequestDto.setFeeName(paymentDto.getFeeName());
    paymentRequestDto.setAmount((double) (paymentDto.getQuantity()* fee.getFeeCharges()));

    PaymentClientResponse paymentClientResponse = paymentClient.performTransaction(paymentDto);
    TransactionDTO transactionDTO = new TransactionDTO();
    PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
    BeanUtils.copyProperties(paymentDto,paymentResponseDto);
    transactionDTO.setReferenceNumber(paymentClientResponse.getReferenceNumber());
    transactionDTO.setStatus(paymentClientResponse.getStatus());
    paymentResponseDto.setTransactionDTO(transactionDTO);
    paymentResponseDto.setReceiptId("RECP-"+ThreadLocalRandom.current().nextInt(0, 100000));
    return paymentResponseDto;
  }
}
