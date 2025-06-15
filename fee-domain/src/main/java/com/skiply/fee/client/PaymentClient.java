package com.skiply.fee.client;

import com.skiply.fee.dto.PaymentClientResponse;
import com.skiply.fee.dto.PaymentDto;


public interface PaymentClient  {
  PaymentClientResponse performTransaction(PaymentDto paymentDto);
}
