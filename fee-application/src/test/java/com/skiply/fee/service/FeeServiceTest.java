package com.skiply.fee.service;

import com.skiply.fee.client.PaymentClient;
import com.skiply.fee.domain.Fee;
import com.skiply.fee.dto.*;
import com.skiply.fee.repository.FeeRepository;
import com.skiply.fee.service.impl.FeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeeServiceTest {

  @Mock
  private FeeRepository feeRepository;

  @Mock
  private PaymentClient paymentClient;

  @InjectMocks
  private FeeServiceImpl feeService;

  private Fee testFee;
  private PaymentDto paymentDto;
  private PaymentClientResponse paymentClientResponse;

  @BeforeEach
  void setUp() {
    testFee = new Fee();
    testFee.setFeeId(1);
    testFee.setGrade("10");
    testFee.setFeeName("Tuition");
    testFee.setFeeCharges(500.0);

    paymentDto = PaymentDto.builder()
        .studentId(123)
        .grade("10")
        .feeName("Tuition")
        .quantity(2)
        .cardNumber("4111111111111111")
        .cardType("VISA")
        .build();

    paymentClientResponse = new PaymentClientResponse();
    paymentClientResponse.setReferenceNumber(134);
    paymentClientResponse.setStatus("SUCCESS");
  }

  @Test
  void saveFee_shouldSaveAndReturnFee() {
    when(feeRepository.save(any(Fee.class))).thenReturn(testFee);

    Fee savedFee = feeService.saveFee(testFee);

    assertNotNull(savedFee);
    assertEquals(testFee.getFeeId(), savedFee.getFeeId());
    verify(feeRepository, times(1)).save(testFee);
  }

  @Test
  void getFeeById_shouldReturnFeeWhenExists() {
    when(feeRepository.findById(1)).thenReturn(Optional.of(testFee));

    Optional<Fee> result = feeService.getFeeById(1);

    assertTrue(result.isPresent());
    assertEquals(testFee.getFeeId(), result.get().getFeeId());
  }

  @Test
  void getFeeById_shouldReturnEmptyWhenNotExists() {
    when(feeRepository.findById(99)).thenReturn(Optional.empty());
    Optional<Fee> result = feeService.getFeeById(99);
    assertTrue(result.isEmpty());
  }

  @Test
  void getFeeByGradeAndFeeName_shouldReturnFeeWhenExists() {
    when(feeRepository.findByGradeAndFeeName("10", "Tuition")).thenReturn(testFee);
    Fee result = feeService.getFeeByGradeAndFeeName("10", "Tuition");

    assertNotNull(result);
    assertEquals(testFee.getGrade(), result.getGrade());
    assertEquals(testFee.getFeeName(), result.getFeeName());
  }

  @Test
  void deleteFee_shouldCallRepositoryDelete() {
    doNothing().when(feeRepository).deleteById(1);
    feeService.deleteFee(1);
    verify(feeRepository, times(1)).deleteById(1);
  }

  @Test
  void payFee_shouldProcessPaymentSuccessfully() {
    when(feeRepository.findByGradeAndFeeName("10", "Tuition")).thenReturn(testFee);
    when(paymentClient.performTransaction(any(PaymentDto.class))).thenReturn(paymentClientResponse);
    PaymentResponseDto response = feeService.payFee(paymentDto);

    assertNotNull(response);
    assertEquals("STU123", response.getStudentId());
    assertEquals("10", response.getGrade());
    assertEquals("Tuition", response.getFeeName());
    assertEquals(2, response.getQuantity());
    assertEquals(1000.0, response.getAmount(), 0.001);
    assertNotNull(response.getReceiptId());
    assertEquals("REF123", response.getTransactionDTO().getReferenceNumber());
    assertEquals("SUCCESS", response.getTransactionDTO().getStatus());

    verify(feeRepository).findByGradeAndFeeName("10", "Tuition");
    verify(paymentClient).performTransaction(any(PaymentDto.class));
  }

  @Test
  void payFee_shouldCalculateCorrectAmount() {
    when(feeRepository.findByGradeAndFeeName("10", "Tuition")).thenReturn(testFee);
    when(paymentClient.performTransaction(any(PaymentDto.class))).thenReturn(paymentClientResponse);

    paymentDto.setQuantity(3);
    PaymentResponseDto response = feeService.payFee(paymentDto);

    assertEquals(1500.0, response.getAmount(), 0.001);
  }

  @Test
  void payFee_shouldGenerateReceiptId() {
    when(feeRepository.findByGradeAndFeeName("10", "Tuition")).thenReturn(testFee);
    when(paymentClient.performTransaction(any(PaymentDto.class))).thenReturn(paymentClientResponse);

    PaymentResponseDto response = feeService.payFee(paymentDto);

    assertNotNull(response.getReceiptId());
    assertTrue(response.getReceiptId().startsWith("RECP-"));
  }

  @Test
  void payFee_shouldCopyTransactionDetailsFromClientResponse() {
    when(feeRepository.findByGradeAndFeeName("10", "Tuition")).thenReturn(testFee);
    when(paymentClient.performTransaction(any(PaymentDto.class))).thenReturn(paymentClientResponse);

    PaymentResponseDto response = feeService.payFee(paymentDto);

    assertEquals("REF123", response.getTransactionDTO().getReferenceNumber());
    assertEquals("SUCCESS", response.getTransactionDTO().getStatus());
  }
}

