package com.booking.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class FlightDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer flightID;
	private String airport_from;
	private String airport_dest;
	private Date flightStartDate;
	private Date flightEndDate;
	private double cost;
	private Integer seatsAvailable;
	private String operation;
	
	
	@Override
	public String toString() {
		return "FlightDTO [flightID=" + flightID + ", airport_from=" + airport_from + ", airport_dest=" + airport_dest
				+ ", flightStartDate=" + flightStartDate + ", flightEndDate=" + flightEndDate + ", cost=" + cost
				+ ", seatsAvailable=" + seatsAvailable + "]";
	}
	
	
}
