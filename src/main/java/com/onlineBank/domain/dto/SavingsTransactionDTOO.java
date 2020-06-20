package com.onlineBank.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString()
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SavingsTransactionDTOO  implements Serializable{

		private static final long serialVersionUID = 1L;

		@JsonFormat(shape = JsonFormat.Shape.NATURAL)
		private long id;
		
		@JsonFormat(shape = JsonFormat.Shape.NATURAL)
		private Date date;
		
		@JsonFormat(shape = JsonFormat.Shape.NATURAL)
		private String description;
		
		@JsonFormat(shape = JsonFormat.Shape.NATURAL)
		private String type;
		
		@JsonFormat(shape = JsonFormat.Shape.NATURAL)
		private String status;
		
		@JsonFormat(shape = JsonFormat.Shape.NATURAL)
		private double amount;
		
		@JsonFormat(shape = JsonFormat.Shape.NATURAL)
		private BigDecimal availableBalance;
		
	
		
	
}
