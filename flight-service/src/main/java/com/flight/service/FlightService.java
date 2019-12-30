package com.flight.service;

import java.util.List;

import com.flight.dto.FlightDTO;

public interface FlightService {

	public List<FlightDTO> getAllFlights();

	public List<FlightDTO> getRouteFlights(String source, String dest);
	
	public FlightDTO getFlight(int flightId);
	
	public boolean updateSeats(int flightId, FlightDTO dto);
}
