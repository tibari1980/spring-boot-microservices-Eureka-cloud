package com.arcesi.orderservice.entities;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ORDERS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_ORDER", name = "SEQUENCE_CODE_ORDER"),
		@UniqueConstraint(columnNames = "UID_ORDER", name = "SEQUENCE_UID_ORDER") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, name = "order_sequence", sequenceName = "order_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
	@Column(name = "CODE_ORDER", nullable = false, insertable = true, unique = true)
	private Long codeOrder;
	@Column(name = "UID_ORDER", unique = true, insertable = true, updatable = false, length = 36)
	private String uidOrder;
	@Column(name = "ID_PRODUCT", insertable = true, updatable = true, nullable = false)
	private Long idProduct;
	@Column(name = "STATUS_ORDER", length = 30, nullable = false, insertable = true)
	private String statusOrder;
	@Column(name = "DATE_ORDER", insertable = true, nullable = false)
	private Instant dateOrder;
	@Column(name = "QUANTITE_ORDER", insertable = true, nullable = false)
	private int quantiteOrder;
	@Column(name = "MONTANT_ORDER", insertable = true, nullable = false)
	private double montantOrder;
	
	@Column(name="PAYEMENT_MODE",insertable = true,nullable = false,updatable = true)
	private String paymentMode;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ClientEntity clientEntity;

}
