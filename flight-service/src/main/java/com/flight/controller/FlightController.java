package com.flight.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dto.FlightDTO;
import com.flight.service.FlightService;

@RestController
@RequestMapping(path = "/flightStatus")
public class FlightController {

	@Autowired
	FlightService flightService;

	@GetMapping(path = "/all")
	public List<FlightDTO> getAllFlightRoutes() {
		return flightService.getAllFlights();
	}

	@GetMapping(path = "/routes")
	public List<FlightDTO> getRouteFlights(@RequestParam("from") String source, @RequestParam("to") Optional<String> dest) {
		System.out.println("Request with params " + source + " and " + dest);
		//check with ritesh how do you add custom quries
		return flightService.getRouteFlights(source, dest.orElse(""));

	}
	
	@PutMapping(path="/update/{flightId}")
	public boolean updateSeats(@PathVariable Integer flightId, @RequestBody FlightDTO dto ) {
		System.out.println("In Put Request:"+flightId+ "|Other:"+dto.getOperation()+"|"+dto.getSeatsAvailable());
		return flightService.updateSeats(flightId, dto);
	}

	@GetMapping("/{flightId}")
	public FlightDTO getFlight(@PathVariable Integer flightId) {
		System.out.println("Received Request for Flight: "+flightId);
		return flightService.getFlight(flightId);
	}
}
