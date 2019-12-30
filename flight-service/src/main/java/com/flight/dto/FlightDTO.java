package com.flight.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FlightDTO {

	private Integer flightID;
	private String airport_from;
	private String airport_dest;
	private Date flightStartDate;
	private Date flightEndDate;
	private double cost;
	private Integer seatsAvailable;
	private String operation;
}
