package com.booking.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookingDTO {

	private Integer bookingID;
	private String firstName;
	private String lastName;
	private Integer flightId;
	private Double amountPaid;
	private String status;
	private Date bookingDate;
	private Integer seatsBoooked;

}
