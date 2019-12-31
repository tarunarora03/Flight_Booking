package com.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.dto.BookingDTO;
import com.booking.dto.FlightDTO;
import com.booking.entity.Booking;
import com.booking.repository.BookingRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	RestTemplate restTemplate;

	
	@Override
	public List<BookingDTO> getAllBookings() {
		List<Booking> getBookings = bookingRepo.findAll();

		List<BookingDTO> allBookings = getBookings.stream().map(temp -> {
			BookingDTO dto = new BookingDTO();
			dto.setBookingID(temp.getBookingid());
			dto.setFirstName(temp.getFirstname());
			dto.setLastName(temp.getLastname());
			dto.setFlightId(temp.getFlightid());
			dto.setStatus(temp.getStatus());
			dto.setAmountPaid(temp.getAmountpaid());
			dto.setBookingDate(temp.getBookingdate());
			dto.setSeatsBoooked(temp.getSeatsbooked());
			return dto;
		}).collect(Collectors.toList());
		return allBookings;
	}

	@Override
	public Integer deleteBooking(int id) {
		try {
			Optional<Booking> booking = bookingRepo.findById(id);
			if(booking.isPresent()) {
				FlightDTO flightDTO = createObj("cancel", booking.get());
				//restTemplate.put("/update/"+id, flightDTO);
				ResponseEntity<String> status = restTemplate.postForEntity("/update/"+id, flightDTO, String.class);
				System.out.println("Status::::::"+status.getBody());
				bookingRepo.deleteById(id);
			}
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	private FlightDTO createObj(String oper, Booking booking) {
		FlightDTO dto = new FlightDTO();
		dto.setOperation(oper);
		dto.setFlightID(booking.getFlightid());
		dto.setSeatsAvailable(booking.getSeatsbooked());
		return dto;
	}
	
	@Override
	@HystrixCommand(fallbackMethod = "serviceDown")
	public Integer addBooking(BookingDTO dto) {
		Booking booking = new Booking();
		System.out.println("Calling Flight Service to get Details.....");
		FlightDTO flightDTO  = restTemplate.getForObject("/"+dto.getFlightId(), FlightDTO.class);
		if(flightDTO != null  && flightDTO.getSeatsAvailable() > 0) {
			flightDTO.setOperation("new");
			flightDTO.setFlightID(dto.getFlightId());
			flightDTO.setSeatsAvailable(dto.getSeatsBoooked());
		}
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("/update/"+dto.getFlightId(), flightDTO, String.class);
		if(responseEntity.getBody().equalsIgnoreCase("success")) {
			booking.setFirstname(dto.getFirstName());
			booking.setLastname(dto.getLastName());
			booking.setFlightid(dto.getFlightId());
			booking.setSeatsbooked(dto.getSeatsBoooked());
			booking.setStatus("Active");
			
			return bookingRepo.saveAndFlush(booking).getBookingid();
		} else {
			System.out.println("Flight Service returned error.......");
			return -1;
		}
	}
	
	private Integer serviceDown(BookingDTO dto) {
		System.out.println("Flight Serivce Down. Unable to do bookings................");
		return -1;
	}

}
