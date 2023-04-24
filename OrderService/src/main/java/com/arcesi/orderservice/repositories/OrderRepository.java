package com.arcesi.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.orderservice.entities.OrderEntity;

@Repository
public interface OrderRepository  extends JpaRepository<OrderEntity, Long>{

}
