package com.arcesi.configservice.services;

import com.arcesi.configservice.dtos.TransactionDetailsDTO;

public interface PaymentService {

	TransactionDetailsDTO doPayment(final TransactionDetailsDTO map);

	TransactionDetailsDTO getPaymentDetailsByOrderId( final Long idOrder);

}
