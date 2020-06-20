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
public class RecipientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
   private String phone;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
   private String email;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
   private String accountNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
   private String description;

	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private UserWithOutRecipientDTO user;



}
