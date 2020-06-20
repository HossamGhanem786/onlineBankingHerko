package com.onlineBank.domain.dto;

import java.io.Serializable;

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
public class TranferBetweenDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	 private String transferFrom;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	 private String transferTo;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	 private Double amount;

}
