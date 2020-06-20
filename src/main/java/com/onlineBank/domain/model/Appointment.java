package com.onlineBank.domain.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "APPOINTMENT")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
public class Appointment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Long id;

	@Column(name = "date")
    private Date date;
	
	@Column(name = "location")
    private String location;
	
	@Column(name = "Confirmed")
	private Boolean confirmed;
	
	@Column(name = "description")
   private String description;
	
	@ManyToOne
	 @JoinColumn(name="user_id")
	private User user;

}
