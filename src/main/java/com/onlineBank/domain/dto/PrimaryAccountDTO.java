package com.onlineBank.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlineBank.domain.model.PrimaryTransaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString()
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PrimaryAccountDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	 private int accountNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	 private  BigDecimal accountBalance;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	 private Collection<PrimaryTransactionDTOO> primaryTransactionList;

}
