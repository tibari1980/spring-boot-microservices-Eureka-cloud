package com.arcesi.orderservice.dtos;

import java.time.Instant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class OrderDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private Long codeOrder;
	private String uidOrder;
	private Long idProduct;
	private String statusOrder;
	private Instant dateOrder;
	private int quantiteOrder;
	private double montantOrder;
	private ClientDTO clientDTO;

}
