package com.onlineBank.domain.dto;

import java.io.Serializable;
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
public class AppointmentDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date date;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private String location;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private Boolean confirmed;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.NATURAL)
	private UserDTOO user;
}
