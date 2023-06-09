package com.arcesi.configservice.services.imp;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.configservice.dtos.TransactionDetailsDTO;
import com.arcesi.configservice.entities.TransactionDetails;
import com.arcesi.configservice.enums.ErrorsCodeEnumeration;
import com.arcesi.configservice.exceptions.EntityNotFoundException;
import com.arcesi.configservice.exceptions.InvalidEntityException;
import com.arcesi.configservice.repositories.TransactionDetailsRepository;
import com.arcesi.configservice.services.PaymentService;
import com.arcesi.configservice.validators.ObjectValidators;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PaymentServiceImp implements PaymentService {

	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectValidators<TransactionDetailsDTO> transactionValidators;

	@Override
	public TransactionDetailsDTO doPayment(TransactionDetailsDTO dto) {
		log.info("Inside methode doPayment of PaymentServiceImp transactionDto :{}", dto.toString());

		Map<String, String> violations = transactionValidators.validate(dto);
		if (!violations.isEmpty()) {
			log.error("There are errors while saving transactiondetails try again ,", violations);
			throw new InvalidEntityException("There are errors while saving transactionDetails try agian",
					ErrorsCodeEnumeration.TRANSACTIONDETAILS_NOT_VALIDE.name(), violations);
		}
		dto.setStatusPayment("SUCCESS");
		dto.setPayementDate(Instant.now());

		TransactionDetails bean = transactionDetailsRepository.saveAndFlush(mapper.map(dto, TransactionDetails.class));
		log.info("Payment saved successufully  Transaction : {}", bean.toString());
		return mapper.map(bean, TransactionDetailsDTO.class);
	}

	@Override
	public TransactionDetailsDTO getPaymentDetailsByOrderId(Long idOrder) {
		log.info("Inside methode getPaymentDetailsByOrderId of PaymentServiceImp Id Order  :{}", idOrder);
		Optional<TransactionDetails> bean = transactionDetailsRepository.findByOrderId(idOrder);
		if (bean.isEmpty()) {
			log.error("Order with id : {}  does not exist in our data base try again", idOrder);
			throw new EntityNotFoundException("Transaction details with id order  : {}  does not exist in our data base try again",
					ErrorsCodeEnumeration.TRANSACTIONDETAILS_NOT_FOUND);
		}
		return mapper.map(bean.get(), TransactionDetailsDTO.class);
	}
}
