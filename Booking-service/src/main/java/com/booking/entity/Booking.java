package com.booking.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingid;
	private String firstname;
	private String lastname;
	private Integer flightid;
	private Double amountpaid;
	private String status;
	private Date bookingdate;
	private Integer seatsbooked;
}
