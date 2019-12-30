package com.flight.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flightID;
	private String airport_from;
	private String airport_dest;
	
	@Temporal(TemporalType.DATE)
	private Date flight_startdate;
	@Temporal(TemporalType.DATE)
	
	private Date flight_enddate;
	private double cost;
	private Integer seatsavailable;
}
