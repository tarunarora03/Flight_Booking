package com.booking.service;

import java.util.List;

import com.booking.dto.BookingDTO;

public interface BookingService {

	public List<BookingDTO> getAllBookings();
	
	public Integer deleteBooking(int id);
	
	public Integer addBooking(BookingDTO dto);
}
