package com.onlineBank.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "PRIMARY_TRANSACTION")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NamedQuery(name = "PrimaryTransaction.findAll", query = "SELECT pt FROM PrimaryTransaction pt")
public class PrimaryTransaction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Long id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="description")
	private String description;
	
	@Column(name="type")
	private String type;
	
	@Column(name="status")
	private String status;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="available_balance")
	private BigDecimal availableBalance;
	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
