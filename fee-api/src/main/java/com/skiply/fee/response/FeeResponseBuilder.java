package com.skiply.fee.response;

import com.skiply.fee.api.model.AddNewFee201Response;
import com.skiply.fee.api.model.PaymentReceiptResponse;
import com.skiply.fee.dto.PaymentResponseDto;
import org.springframework.beans.BeanUtils;

public class FeeResponseBuilder {

  public static AddNewFee201Response buildFeeCreateResponse(Integer feeId){
    AddNewFee201Response addNewFee201Response = new AddNewFee201Response();
    addNewFee201Response.setFeeId(feeId);
    return addNewFee201Response;
  }

  public static PaymentReceiptResponse buildPaymentReceiptResponse(PaymentResponseDto paymentResponseDto){
    PaymentReceiptResponse paymentReceiptResponse = new PaymentReceiptResponse();
    BeanUtils.copyProperties(paymentResponseDto,paymentReceiptResponse);
    paymentResponseDto.setTransactionDTO(paymentResponseDto.getTransactionDTO());
    return paymentReceiptResponse;
  }
}
