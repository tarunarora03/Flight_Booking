package com.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.BookingDTO;
import com.booking.service.BookingService;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@GetMapping(path="/all")
	public List<BookingDTO> getAllBookings(){
		return bookingService.getAllBookings();
	}
	
	@DeleteMapping("/{id}")
	public String deleteBooking(@PathVariable int id) {
		int stat = bookingService.deleteBooking(id);
		return stat==0 ? "Sucess":"failed";
	}
	
	@PostMapping
	public int addNewBooking() {
		return 0;
	}
}
