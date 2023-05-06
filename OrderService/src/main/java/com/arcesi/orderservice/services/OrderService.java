package com.arcesi.orderservice.services;

import com.arcesi.orderservice.dtos.OrderDTO;

public interface OrderService {

	OrderDTO createOrder(final OrderDTO orderDto,final Long idClient);

	OrderDTO getOneOrderById(final  Long idOrder);

	void deleteOrder(final  Long idOrder);

}
