package com.arcesi.orderservice.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.orderservice.repositories.ClientRepository;
import com.arcesi.orderservice.repositories.OrderRepository;
import com.arcesi.orderservice.services.ClientService;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ClientServiceImp implements ClientService{

	@Autowired
	private ClientRepository clientRepository; 
    @Autowired
    private OrderRepository orderRepository;
    
    
}
