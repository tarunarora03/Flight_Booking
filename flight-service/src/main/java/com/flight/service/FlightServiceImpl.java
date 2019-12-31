package com.flight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dto.FlightDTO;
import com.flight.entity.Flight;
import com.flight.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepo;

	@Override
	public List<FlightDTO> getAllFlights() {
		List<FlightDTO> allFlights = new ArrayList<FlightDTO>();

		List<Flight> aFlight = flightRepo.findAll();

		allFlights = aFlight.stream().map(temp -> {
			FlightDTO dto = new FlightDTO();
			dto.setFlightID(temp.getFlightID());
			dto.setAirport_from(temp.getAirport_from());
			dto.setAirport_dest(temp.getAirport_dest());
			dto.setFlightStartDate(temp.getFlight_startdate());
			dto.setFlightEndDate(temp.getFlight_enddate());
			dto.setCost(temp.getCost());
			dto.setSeatsAvailable(temp.getSeatsavailable());
			return dto;
		}).collect(Collectors.toList());

		return allFlights;
	}

	@Override
	public List<FlightDTO> getRouteFlights(String source, String dest) {
		List<FlightDTO> allFlights = new ArrayList<FlightDTO>();
		List<Flight> aFlight = flightRepo.findBySourceCity(source);
		allFlights = aFlight.stream().map(temp -> {
			FlightDTO dto = new FlightDTO();
			dto.setFlightID(temp.getFlightID());
			dto.setAirport_from(temp.getAirport_from());
			dto.setAirport_dest(temp.getAirport_dest());
			dto.setFlightStartDate(temp.getFlight_startdate());
			dto.setFlightEndDate(temp.getFlight_enddate());
			dto.setCost(temp.getCost());
			dto.setSeatsAvailable(temp.getSeatsavailable());
			return dto;
		}).collect(Collectors.toList());
		return allFlights;
	}

	@Override
	public FlightDTO getFlight(int flightId) {

		Optional<Flight> flight = flightRepo.findById(flightId);
		FlightDTO dto = new FlightDTO();
		if(flight.isPresent()) {
			dto.setFlightID(flight.get().getFlightID());
			dto.setAirport_from(flight.get().getAirport_from());
			dto.setAirport_dest(flight.get().getAirport_dest());
			dto.setFlightStartDate(flight.get().getFlight_startdate());
			dto.setFlightEndDate(flight.get().getFlight_enddate());
			dto.setCost(flight.get().getCost());
			dto.setSeatsAvailable(flight.get().getSeatsavailable());
		}
						
		return dto;
	}

	@Override
	public String updateSeats(int flightId, FlightDTO dto) {
		try {
			Optional<Flight> flight = flightRepo.findById(flightId);
			if(flight.isPresent()) {
				if(dto.getOperation().equalsIgnoreCase("cancel")) 
					flight.get().setSeatsavailable(flight.get().getSeatsavailable() + dto.getSeatsAvailable());
				else
					flight.get().setSeatsavailable(flight.get().getSeatsavailable() - dto.getSeatsAvailable());

				flightRepo.save(flight.get());
			}
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
	}

}
