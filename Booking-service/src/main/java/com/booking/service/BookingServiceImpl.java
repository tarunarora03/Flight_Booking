package com.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booking.dto.BookingDTO;
import com.booking.dto.FlightDTO;
import com.booking.entity.Booking;
import com.booking.repository.BookingRepository;

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
//			FlightDTO flightDTO = restTemplate.getForObject("/"+id, FlightDTO.class);
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//			System.out.println(flightDTO.toString());
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			
			Optional<Booking> booking = bookingRepo.findById(id);
			if(booking.isPresent()) {
				FlightDTO flightDTO = createObj("cancel", booking.get());
				restTemplate.put("/update/"+id, flightDTO);
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
	public Integer addBooking(BookingDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
