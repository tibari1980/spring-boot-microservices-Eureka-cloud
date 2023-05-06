package com.arcesi.orderservice.jddtest;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arcesi.orderservice.entities.ClientEntity;
import com.arcesi.orderservice.entities.OrderEntity;
import com.arcesi.orderservice.repositories.ClientRepository;
import com.arcesi.orderservice.repositories.OrderRepository;

@Component
public class JDDTestApp implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 150; i++) {
			ClientEntity client = ClientEntity.builder()
					.uidClient(UUID.randomUUID().toString()).dateNaissanceClient(new Date())
					.email("tibarinewdzigh" + i + "@gmail.com").nomClient("tibari" + i).prenomClient("zeroual" + i)
					.sexClient("M")
					.telephoneClient("0625491640")
					.adressePostaleClient("1" + i + " place des alpes 78280 Guyancout").build();

			//ClientEntity clientSaved = clientRepository.save(client);
			OrderEntity order = OrderEntity.builder()
					 
				 
					.uidOrder(UUID.randomUUID().toString())
					.dateOrder(Instant.now()).idProduct(Long.valueOf(i))
					.montantOrder((i % 2 != 0) ? 444 + 9 : 900 + 10)
					.quantiteOrder((i % 2 != 0) ? 1 + 4 : 1 + 3)
					.paymentMode("CASH")
					.statusOrder("VALIDEE")
					//.clientEntity(clientSaved)
					.build();

			//orderRepository.save(order);
		}

	}
}
